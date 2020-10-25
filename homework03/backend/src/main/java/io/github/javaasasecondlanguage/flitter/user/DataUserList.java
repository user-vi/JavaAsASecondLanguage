package io.github.javaasasecondlanguage.flitter.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DataUserList {

    @JsonProperty("data")
    private Map<String, String> users;

    public DataUserList() {
    }

    public DataUserList(List<String> list) {
        users = users;
    }

}
