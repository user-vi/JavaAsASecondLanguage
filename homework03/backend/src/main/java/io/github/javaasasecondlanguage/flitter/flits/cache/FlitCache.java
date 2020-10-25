package io.github.javaasasecondlanguage.flitter.flits.cache;

import io.github.javaasasecondlanguage.flitter.flits.Flit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FlitCache {
    private final int capacity;
    private Cache cache;

    public FlitCache(int capacity) {
        this.capacity = capacity;
        cache = new Cache(capacity);
    }

    public void add(String userToken, TopElement flit) {
        cache.put(userToken, flit);
    }

    public List<TopElement> getTop() {
        return new ArrayList<>(cache.values());
    }
}

class Cache extends LinkedHashMap<String, TopElement> {
    private final int capacity;

    public Cache(int capacity) {
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, TopElement> eldest) {
        return size() > capacity;
    }

}
