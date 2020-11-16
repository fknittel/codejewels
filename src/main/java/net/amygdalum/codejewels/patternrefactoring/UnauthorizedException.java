package net.amygdalum.codejewels.patternrefactoring;

public class UnauthorizedException extends Exception {

    private String user;

    public UnauthorizedException(String user) {
        super("user " + user + " is not authorized");
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
