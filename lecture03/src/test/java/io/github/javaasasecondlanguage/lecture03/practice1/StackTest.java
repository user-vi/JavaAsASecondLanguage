package io.github.javaasasecondlanguage.lecture03.practice1;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    void singlePushAndPop() {
        var s = new Stack<Number>();
        assertTrue(s.isEmpty());
        s.push(1);
        assertFalse(s.isEmpty());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void popFromEmptyStack() {
        var s = new Stack<Number>();
        assertThrows(EmptyStackException.class, s::pop);
    }

    @Test
    void pushAllAndPopAll() {
        var s = new Stack<Number>();
        var orig = List.of(1, 2);
        s.pushAll(orig);
        assertFalse(s.isEmpty());
        var dest = new ArrayList<Object>();
        s.popAll(dest);
        assertTrue(s.isEmpty());
        Collections.reverse(dest);
        assertEquals(orig, dest);
    }
}