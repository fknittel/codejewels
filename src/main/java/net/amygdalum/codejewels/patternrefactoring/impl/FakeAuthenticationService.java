package net.amygdalum.codejewels.patternrefactoring.impl;

import static java.util.Arrays.asList;

import java.util.List;
import net.amygdalum.codejewels.patternrefactoring.AuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.Token;
import net.amygdalum.codejewels.patternrefactoring.UnauthorizedException;

public class FakeAuthenticationService implements AuthenticationService {

    private List<String> authenticated;

    public FakeAuthenticationService(String... authenticated) {
        this.authenticated = asList(authenticated);
    }

    @Override
    public boolean isAllowed(String user) {
        return authenticated.contains(user);
    }

    @Override
    public Token getToken(String id, String user) throws UnauthorizedException {
        if (authenticated.contains(user)) {
            return new FakeToken(id, user, "allowed");
        } else {
            throw new UnauthorizedException(user);
        }
    }

}
