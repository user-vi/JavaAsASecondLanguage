package io.github.javaasasecondlanguage.homework04.ops.reducers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Reducer;

import java.util.Map;

/**
 * Returns at most maxAmount records per group.
 */
public class FirstNReducer implements Reducer {

    public FirstNReducer(int maxAmount) {
        throw new IllegalStateException("You must implement this");
    }

    @Override
    public void apply(Record inputRecord, Collector collector, Map<String, Object> groupByEntries) {
        throw new IllegalStateException("You must implement this");
    }

    @Override
    public void signalGroupWasFinished(Collector collector, Map<String, Object> groupByEntries) {
        throw new IllegalStateException("You must implement this");
    }
}
