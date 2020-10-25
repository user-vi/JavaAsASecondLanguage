package io.github.javaasasecondlanguage.flitter.user;

import java.util.UUID;

public class Flit {
    private String flitToken;
    private String flitContent;

    public String getFlitContent() {
        return flitContent;
    }

    public String getFlitToken() {
        return flitToken;
    }

    public Flit(String flitContent) {
        this.flitToken = UUID.randomUUID().toString();
        this.flitContent = flitContent;
    }
}
