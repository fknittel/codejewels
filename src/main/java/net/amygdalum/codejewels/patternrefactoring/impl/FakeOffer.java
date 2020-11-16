package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.Offer;

public class FakeOffer implements Offer {

    private long offerId;

    public FakeOffer(long offerId) {
        this.offerId = offerId;
    }

    public long getOfferId() {
        return offerId;
    }

    @Override
    public String toString() {
        return String.valueOf(offerId);
    }
}
