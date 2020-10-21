package io.github.javaasasecondlanguage.flitter.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private UserName username;
    private String userToken;

    public String getUsername() {
        return username.getUserName();
    }

    public String getUserToken() {
        return userToken;
    }

    public User(UserName username) {
        this.username = username;
        this.userToken = UUID.randomUUID().toString();
    }

}
