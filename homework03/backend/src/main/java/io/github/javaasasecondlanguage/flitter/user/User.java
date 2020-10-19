package io.github.javaasasecondlanguage.flitter.user;

import java.util.UUID;

public class User {
    private String username;
    private String userToken;

    public String getUsername() {
        return username;
    }

    public String getUserToken() {
        return userToken;
    }

    public User(String username) {
        this.username = username;
        this.userToken = UUID.randomUUID().toString();
    }
}
