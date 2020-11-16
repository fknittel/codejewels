package net.amygdalum.codejewels.patternrefactoring;

public interface BusinessService {

    ServiceVersion getServiceVersion();

    BusinessService authenticate(Token token);

    Offer getOffer(long offerId);

    Price addPosition(long offerId, double price);

}
