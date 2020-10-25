package io.github.javaasasecondlanguage.flitter.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationResult {

    @JsonProperty("data")
    private User user;
    private String errorMessage;

    public UserRegistrationResult(User user, String errorMessage) {
        this.user = user;
        this.errorMessage = errorMessage;
    }

    public User getUser() {
        return user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
