package io.github.javaasasecondlanguage.flitter.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.github.javaasasecondlanguage.flitter.utils.CollectionTestUtils.castToMaps;

public class ArrayResult {

    private Object[] data;
    private String errorMessage;

    public ArrayResult() {
    }

    public ArrayResult(Object[] data, String errorMessage) {
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getDataAsList() {
        if (data != null) {
            return (List<T>) List.of(data);
        } else {
            return List.of();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<Map<String, Object>> getDataAsMaps() {
        if (data != null) {
            return castToMaps(data);
        } else {
            return List.of();
        }
    }

    public boolean isFail() {
        return errorMessage != null;
    }

    @Override
    public String toString() {
        return "ArrayResult{" +
                "data=" + Arrays.toString(data) +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
