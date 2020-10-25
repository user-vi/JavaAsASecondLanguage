package io.github.javaasasecondlanguage.flitter.flits;

import java.util.UUID;

public class Flit {
    private String flitToken;
    private String flitContent;
    private String userToken;

    public String getFlitContent() {
        return flitContent;
    }

    public String getFlitToken() {
        return flitToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public Flit(String flitContent, String userToken) {
        this.flitToken = UUID.randomUUID().toString();
        this.flitContent = flitContent;
        this.userToken = userToken;
    }
}
