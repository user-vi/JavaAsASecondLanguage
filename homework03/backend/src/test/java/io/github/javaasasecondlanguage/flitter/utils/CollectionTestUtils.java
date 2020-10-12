package io.github.javaasasecondlanguage.flitter.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.javaasasecondlanguage.flitter.utils.ExpectedStatus.EXPECT_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CollectionTestUtils {

    public static <A, B> void assertSetEquals(Collection<A> expectedCollection, Collection<B> actualCollection) {
        assertEquals(
                new HashSet<>(expectedCollection),
                new HashSet<>(actualCollection)
        );
    }

    public static <A, B> void assertMapsEqualByKeys(
            Collection<Map<String, A>> expectedCollection,
            Collection<Map<String, B>> actualCollection,
            String... keys
    ) {
        assertEquals(
                retainKeys(expectedCollection, keys),
                retainKeys(actualCollection, keys)
        );
    }

    public static <A> Set<Map<String, A>> retainKeys(Collection<Map<String, A>> inputCollection, String... retainedKeys) {
        return inputCollection
                .stream()
                .map(m -> retainKeys(m, retainedKeys))
                .collect(Collectors.toSet());
    }

    public static <A> Map<String, A> retainKeys(Map<String, A> inputMap, String... retainedKeys) {
        var retainedKeySet = Set.of(retainedKeys);
        var outputMap = new LinkedHashMap<>(inputMap);
        outputMap.keySet().retainAll(retainedKeySet);
        return outputMap;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> castToMap(Object obj) {
        if (obj instanceof Map) {
            return (Map<String, Object>) obj;
        } else {
            throw new IllegalArgumentException("Object is not a map: " + obj);
        }
    }

    public static List<Map<String, Object>> castToMaps(Object[] arr) {
        var outputList = new ArrayList<Map<String, Object>>();

        for (var obj : arr) {
            Map<String, Object> map = castToMap(obj);
            outputList.add(map);
        }

        return outputList;
    }

    public static void assertThatStatusIsExpected(ExpectedStatus expectedStatus, ArrayResult result) {
        var data = result.getData();
        var errorMessage = result.getErrorMessage();
        assertThatStatusIsExpected(expectedStatus, errorMessage, data);
    }

    public static void assertThatStatusIsExpected(ExpectedStatus expectedStatus, MapResult result) {
        var data = result.getData();
        var errorMessage = result.getErrorMessage();
        assertThatStatusIsExpected(expectedStatus, errorMessage, data);
    }

    private static void assertThatStatusIsExpected(ExpectedStatus expectedStatus,
                                                   String errorMessage,
                                                   Object data) {
        boolean expectedSuccess = (expectedStatus == EXPECT_SUCCESS);
        var receivedSuccess = (errorMessage == null);

        if (!expectedSuccess && receivedSuccess) {
            fail("Expected fail in this response, but got success instead. Received data: "+data);
        }
        if (expectedSuccess && !receivedSuccess) {
            fail("Expected success in this response, but got fail instead. Message: "+errorMessage);
        }
    }
}
