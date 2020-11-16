package net.amygdalum.codejewels.patternrefactoring;

public interface AuthenticationService {

    @Deprecated
    boolean isAllowed(String user);

    Token getToken(String id, String user) throws UnauthorizedException;

}
