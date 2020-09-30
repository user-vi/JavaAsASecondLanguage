package io.github.javaasasecondlanguage.lecture03.examples;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayVsList {
    @Test
    void arrayIsCovariant() {
        Object[] array = new Integer[1];
        assertThrows(java.lang.ArrayStoreException.class, () -> array[0] = "I am not an Integer");
    }

    @Test
    void genericsAreInvariant() {
        /*
        ArrayList<Object> list = new ArrayList<Integer>(); // Incompatible types
        list.add("I am not an Integer");
        */
    }
}
