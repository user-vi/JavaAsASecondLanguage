package io.github.javaasasecondlanguage.flitter.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.javaasasecondlanguage.flitter.user.User;

public class Data {

    @JsonProperty("data")
    private User user;

    public Data() {
    }
    public Data(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
