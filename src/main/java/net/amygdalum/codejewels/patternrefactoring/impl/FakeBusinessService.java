package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.BusinessService;
import net.amygdalum.codejewels.patternrefactoring.Offer;
import net.amygdalum.codejewels.patternrefactoring.Price;
import net.amygdalum.codejewels.patternrefactoring.ServiceVersion;
import net.amygdalum.codejewels.patternrefactoring.Token;

public class FakeBusinessService implements BusinessService {

    @Override
    public BusinessService authenticate(Token token) {
        return new AuthenticatedBusinessService(token);
    }

    @Override
    public ServiceVersion getServiceVersion() {
        throw new RuntimeException("authenticate before request");
    }

    @Override
    public Offer getOffer(long offerId) {
        throw new RuntimeException("authenticate before request");
    }

    @Override
    public Price addPosition(long offerId, double price) {
        throw new RuntimeException("authenticate before request");
    }

}
