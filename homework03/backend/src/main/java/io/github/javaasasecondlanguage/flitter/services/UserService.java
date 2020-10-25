package io.github.javaasasecondlanguage.flitter.services;

import io.github.javaasasecondlanguage.flitter.user.User;
import io.github.javaasasecondlanguage.flitter.user.UserRegistrationResult;

import java.util.*;

public class UserService {
    private Map<String, String> users = new HashMap<>();

    public String getUserByToken(String token) {
        return users.get(token);
    }

    public void clear() {
        users.clear();
    }

    public UserRegistrationResult register(String userName) {
        if (users.containsValue(userName)) {
            return new UserRegistrationResult(null, "This name is already taken");
        } else {
            User user = new User(userName);
            users.put(user.getUserToken(), user.getUserName());
            return new UserRegistrationResult(user, null);
        }
    }

    public boolean isUserNameRegistered(String userName) {
        if (users.containsValue(userName)) {
            return true;
        } else return false;
    }

    public List<String> list() {
        return new ArrayList<>(users.values());
    }
}
