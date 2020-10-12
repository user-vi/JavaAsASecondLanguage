package io.github.javaasasecondlanguage.flitter.utils;

import java.util.Map;

public class MapResult {

    private Map<String, Object> data;
    private String errorMessage;

    public MapResult() {
    }

    public MapResult(Map<String, Object> data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "MapResult{" +
                "data=" + data +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
