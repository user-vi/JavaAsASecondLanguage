package io.github.javaasasecondlanguage.flitter.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private String userToken;
    private String username;


    public String getUsername() {
        return username;
    }

    public String getUserToken() {
        return userToken;
    }

    public User(String username) {
        this.userToken = UUID.randomUUID().toString();
        this.username = username;
    }



}
