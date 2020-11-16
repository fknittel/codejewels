package net.amygdalum.codejewels.patternrefactoring;

import java.util.Map;

public class DBRequest {

    private String id;
    private String user;
    private Map<String, String> attributes;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAttribute(String attribute) {
        return attributes.get(attribute);
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
