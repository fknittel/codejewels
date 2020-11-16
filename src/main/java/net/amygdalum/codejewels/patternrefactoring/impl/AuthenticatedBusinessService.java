package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.BusinessService;
import net.amygdalum.codejewels.patternrefactoring.Offer;
import net.amygdalum.codejewels.patternrefactoring.Price;
import net.amygdalum.codejewels.patternrefactoring.ServiceVersion;
import net.amygdalum.codejewels.patternrefactoring.Token;

public class AuthenticatedBusinessService implements BusinessService {

    private Token token;

    public AuthenticatedBusinessService(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public BusinessService authenticate(Token token) {
        return this;
    }

    @Override
    public ServiceVersion getServiceVersion() {
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakeServiceVersion(id);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

    @Override
    public Offer getOffer(long offerId) {
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakeOffer(offerId);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

    @Override
    public Price addPosition(long offerId, double price) {
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakePrice(offerId, price);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

}
