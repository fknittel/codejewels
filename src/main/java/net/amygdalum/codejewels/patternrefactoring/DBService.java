package net.amygdalum.codejewels.patternrefactoring;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBService {

    private static final Logger LOG = LoggerFactory.getLogger(DBService.class);

    private AuthenticationService auth;
    private SerializationService serializer;
    private BusinessService business;
    private BusinessCommandExecutor businessCommandExecutor;
    private UnauthenticatedResponseFactory unauthenticatedResponseFactory;

    public DBService(AuthenticationService auth, SerializationService serializer, BusinessService business) {
        this.auth = auth;
        this.serializer = serializer;
        this.business = business;
        this.businessCommandExecutor = new RequestLoggingWrappingExecutor(new AuthenticatingWrappingExecutor(auth, new ExecutingCommandExecutor()));
        this.unauthenticatedResponseFactory = new UnauthenticatedResponseFactory(this.serializer);
    }

    public DBResponse getServiceVersion(DBRequest request) {
        return execute(request, DBService::handleGetServiceVersion);
    }

    public DBResponse addPosition(DBRequest request) {
        return execute(request, DBService::handleAddPosition);
    }

    public DBResponse requestOffer(DBRequest request) {
        return execute(request, DBService::handleRequestOffer);
    }

    private static Object handleGetServiceVersion(Map<String, String> attributes, BusinessService authenticatedBusiness) throws BusinessException {
        ServiceVersion serviceVersion = authenticatedBusiness.getServiceVersion();
        if (serviceVersion == null) {
            throw new BusinessException("cannot find service version");
        }
        return serviceVersion;
    }

    private static Object handleAddPosition(Map<String, String> attributes, BusinessService authenticatedBusiness) throws BusinessException {
        String rawOfferId = attributes.get("offerId");
        if (!isLong(rawOfferId)) {
            throw new BusinessException("no offerId");
        }
        long offerId = Long.parseLong(rawOfferId);

        double price = Double.parseDouble(attributes.get("price"));

        Price finalPrice = authenticatedBusiness.addPosition(offerId, price);
        if (finalPrice == null) {
            throw new BusinessException("cannot find price for id " + offerId);
        }
        return finalPrice;
    }

    private static Object handleRequestOffer(Map<String, String> attributes, BusinessService authenticatedBusiness) throws BusinessException {
        long offerId = Long.parseLong(attributes.get("offerId"));

        Offer offer = authenticatedBusiness.getOffer(offerId);
        if (offer == null) {
            throw new BusinessException("cannot find offer for id " + offerId);
        }
        return offer;
    }

    private DBResponse execute(DBRequest request, BusinessCommand businessCommand) {
        return businessCommandExecutor.execute(request, business, unauthenticatedResponseFactory, businessCommand);
    }

    private interface BusinessCommandExecutor {
        DBResponse execute(DBRequest request, BusinessService business, ResponseFactory responseFactory, BusinessCommand businessCommand);
    }

    private interface BusinessCommand {
        Object createResponse(Map<String, String> attributes, BusinessService authenticatedBusiness) throws BusinessException;
    }

    private static class UnauthenticatedResponseFactory implements ResponseFactory {
        private SerializationService serializer;

        private UnauthenticatedResponseFactory(SerializationService serializer) {
            this.serializer = serializer;
        }

        @Override
        public DBResponse success(Object payload) {
            return response(200, serializer.serialize(payload));
        }

        @Override
        public DBResponse businessFailure(String failureMessage) {
            return response(422, failureMessage);
        }

        @Override
        public DBResponse authorizationFailure() {
            return response(401, "Access denied");
        }

        @Override
        public DBResponse unexpectedFailure(String failureMessage) {
            return response(500, failureMessage);
        }

        private DBResponse response(int status, String payload) {
            DBResponse response = new DBResponse();
            response.setStatus(status);
            response.setPayload(payload);
            return response;
        }
    }

    private static class ResponseAuthenticationWrapperFactory implements ResponseFactory {
        private ResponseAuthenticator responseAuthenticator;
        private ResponseFactory innerResponseFactory;

        private ResponseAuthenticationWrapperFactory(ResponseFactory innerResponseFactory, ResponseAuthenticator responseAuthenticator) {
            this.innerResponseFactory = innerResponseFactory;
            this.responseAuthenticator = responseAuthenticator;
        }

        @Override
        public DBResponse success(Object payload) {
            return responseAuthenticator.authenticate(innerResponseFactory.success(payload));
        }

        @Override
        public DBResponse authorizationFailure() {
            return responseAuthenticator.authenticate(innerResponseFactory.authorizationFailure());
        }

        @Override
        public DBResponse businessFailure(String failureMessage) {
            return responseAuthenticator.authenticate(innerResponseFactory.businessFailure(failureMessage));
        }

        @Override
        public DBResponse unexpectedFailure(String failureMessage) {
            return responseAuthenticator.authenticate(innerResponseFactory.unexpectedFailure(failureMessage));
        }
    }

    private static class ResponseAuthenticator {
        private String user;
        private String role;

        private ResponseAuthenticator(String user, String role) {
            this.user = user;
            this.role = role;
        }

        public DBResponse authenticate(DBResponse unauthenticatedResponse) {
            DBResponse response = new DBResponse();
            response.setUser(user);
            response.setRole(role);
            response.setStatus(unauthenticatedResponse.getStatus());
            response.setPayload(unauthenticatedResponse.getPayload());
            return response;
        }
    }

    public static class BusinessException extends Exception {
        public BusinessException(String message) {
            super(message);
        }
    }

    private static boolean isLong(String str) {
        return str.matches("\\d+");
    }

    public interface ResponseFactory {
        DBResponse success(Object payload);

        DBResponse businessFailure(String failureMessage);

        DBResponse authorizationFailure();

        DBResponse unexpectedFailure(String failureMessage);
    }

    private static class RequestLoggingWrappingExecutor implements BusinessCommandExecutor {
        private BusinessCommandExecutor inner;

        public RequestLoggingWrappingExecutor(BusinessCommandExecutor next) {
            this.inner = next;
        }

        @Override
        public DBResponse execute(DBRequest request, BusinessService businessService, ResponseFactory responseFactory, BusinessCommand businessCommand) {
            LOG.info("starting request " + request.getId());
            try {
                return inner.execute(request, businessService, responseFactory, businessCommand);
            } finally {
                LOG.info("finished request " + request.getId());
            }
        }
    }

    private static class AuthenticatingWrappingExecutor implements BusinessCommandExecutor {
        private BusinessCommandExecutor inner;
        private AuthenticationService auth;

        public AuthenticatingWrappingExecutor(AuthenticationService auth, BusinessCommandExecutor inner) {
            this.inner = inner;
            this.auth = auth;
        }

        @Override
        public DBResponse execute(DBRequest request, BusinessService businessService, ResponseFactory responseFactory, BusinessCommand businessCommand) {
            String user = request.getUser();
            try {
                Token token = auth.getToken(request.getId(), user);

                BusinessService authenticatedBusiness = businessService.authenticate(token);
                ResponseFactory authenticatedResponseFactory = new ResponseAuthenticationWrapperFactory(responseFactory,
                    new ResponseAuthenticator(user, token.getRole()));

                return inner.execute(request, authenticatedBusiness, authenticatedResponseFactory, businessCommand);
            } catch (UnauthorizedException e) {
                LOG.warn("user " + user + " is not authorized");
                return responseFactory.authorizationFailure();
            }
        }
    }

    private static class ExecutingCommandExecutor implements BusinessCommandExecutor {
        @Override
        public DBResponse execute(DBRequest request, BusinessService businessService, ResponseFactory responseFactory, BusinessCommand businessCommand) {
            try {
                Object result = businessCommand.createResponse(request.getAttributes(), businessService);
                return responseFactory.success(result);
            } catch (BusinessException e) {
                return responseFactory.businessFailure(e.getMessage());
            } catch (Exception e) {
                LOG.warn("unexpected exception: " + e.getMessage());
                return responseFactory.unexpectedFailure(e.getMessage());
            }
        }
    }
}
