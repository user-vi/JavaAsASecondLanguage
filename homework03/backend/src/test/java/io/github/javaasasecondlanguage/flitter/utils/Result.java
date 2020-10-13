package io.github.javaasasecondlanguage.flitter.utils;

import java.util.List;
import java.util.Map;

import static io.github.javaasasecondlanguage.flitter.utils.CollectionTestUtils.castToMap;
import static io.github.javaasasecondlanguage.flitter.utils.CollectionTestUtils.castToMapList;
import static java.lang.String.format;

public class Result {

    private Object data;
    private String errorMessage;

    public Result() {
    }

    public Result(Object data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isList() {
        return data != null && data instanceof List;
    }

    public boolean isMap() {
        return data != null && data instanceof Map;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getDataAsList() {
        if (isList()) {
            return (List<T>) data;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<Map<String, Object>> getDataAsMapsList() {
        if (isList()) {
            return castToMapList(data);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getDataAsMap() {
        if (isMap()) {
            return castToMap(data);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return format("Result{data=%s, errorMessage='%s'}", data, errorMessage);
    }
}
