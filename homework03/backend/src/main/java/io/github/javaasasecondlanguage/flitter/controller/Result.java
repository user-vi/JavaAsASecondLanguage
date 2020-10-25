package io.github.javaasasecondlanguage.flitter.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result {
    @JsonProperty("data")
    private Object data;
    @JsonProperty("errorMessage")
    private String errorMessage;

    public Result() {
    }

    public Result(Object data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }
}
