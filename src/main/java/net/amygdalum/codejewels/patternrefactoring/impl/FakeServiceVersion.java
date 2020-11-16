package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.ServiceVersion;

public class FakeServiceVersion implements ServiceVersion {

    private long id;

    public FakeServiceVersion(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
