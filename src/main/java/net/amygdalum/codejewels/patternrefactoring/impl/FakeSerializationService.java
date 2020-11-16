package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.SerializationService;

public class FakeSerializationService implements SerializationService {

    @Override
    public String serialize(Object object) {
        return object.toString();
    }

}
