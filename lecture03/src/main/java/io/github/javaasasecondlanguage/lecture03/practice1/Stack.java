package io.github.javaasasecondlanguage.lecture03.practice1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Classic LIFO stack
 * @param <E> the type of elements in stack
 */
public class Stack<E> {
    private List<E> list = new ArrayList<>();

    public Stack() {
    }

    public void push(E e) {
        list.add(e);
    }

    public E pop() {
        if (list.isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void popAll(Collection<? super E> dst) {
        while (!isEmpty())
            dst.add(pop());
    }


    public void pushAll(Iterable<? extends E> src) {
        for (E e : src)
            push(e);
    }

}