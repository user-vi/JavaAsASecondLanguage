package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.user.DataRegister;
import io.github.javaasasecondlanguage.flitter.user.User;

import java.util.*;

public class UserService {
    private Map<String, String> users = new HashMap<>();

    public String getUserByToken(String token){
        return users.get(token);
    }
    public void clear(){
        users.clear();
    }

    public User register(String userName){
        User user = new User(userName);
        users.put(user.getUserToken(), user.getUsername());
        return user;
    }

    public List<String> list(){
        return new ArrayList<>(users.values());
    }
}
