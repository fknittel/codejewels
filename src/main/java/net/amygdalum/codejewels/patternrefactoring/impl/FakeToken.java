package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.Token;

public class FakeToken implements Token {

    private String user;
    private String role;
    private String id;

    public FakeToken(String id, String user, String role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String getRole() {
        return role;
    }

}
