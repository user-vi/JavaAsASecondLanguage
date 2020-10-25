package io.github.javaasasecondlanguage.flitter.flits;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultFlit {
    @JsonProperty("data")
    private String result;

    public ResultFlit(String result) {
        this.result = result;
    }


}
