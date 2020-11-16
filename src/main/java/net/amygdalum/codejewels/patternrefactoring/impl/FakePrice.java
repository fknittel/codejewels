package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.Price;

public class FakePrice implements Price {

    private long offerId;
    private double price;

    public FakePrice(long offerId, double price) {
        this.offerId = offerId;
        this.price = price;
    }

    public long getOfferId() {
        return offerId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.valueOf(offerId) + ":" + price;
    }
}
