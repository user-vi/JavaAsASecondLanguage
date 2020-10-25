package io.github.javaasasecondlanguage.flitter.user;

import java.util.UUID;

public class User {
    private String userToken;
    private String userName;


    public String getUserName() {
        return userName;
    }

    public String getUserToken() {
        return userToken;
    }

    public User(String userName) {
        this.userToken = UUID.randomUUID().toString();
        this.userName = userName;
    }



}
