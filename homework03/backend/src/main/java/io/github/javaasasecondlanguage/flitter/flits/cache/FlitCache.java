package io.github.javaasasecondlanguage.flitter.flits.cache;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.List;

public class FlitCache {
    private final int capacity;
    private CircularFifoQueue cache;

    public FlitCache(int capacity) {
        this.capacity = capacity;
        cache = new CircularFifoQueue(capacity);
    }

    public void add(TopElement flit) {
        cache.add(flit);
    }

    public List<TopElement> getTop() {
        return new ArrayList<>(cache);
    }

    public void clear() {
        cache.clear();
    }

    @Override
    public String toString() {
        return "FlitCache{" +
                "capacity=" + capacity +
//                ", cache=" + cache +
                '}';
    }
}

//class Cache extends LinkedHashMap<String, TopElement> {
//    private final int capacity;
//
//    public Cache(int capacity) {
//        super(capacity);
//        this.capacity = capacity;
//    }
//
//    @Override
//    protected boolean removeEldestEntry(Map.Entry<String, TopElement> eldest) {
//        return size() > capacity;
//    }
//
//}
