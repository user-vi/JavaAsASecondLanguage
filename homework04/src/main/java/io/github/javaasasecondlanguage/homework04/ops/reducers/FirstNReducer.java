package io.github.javaasasecondlanguage.homework04.ops.reducers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Reducer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Returns at most maxAmount records per group.
 */
public class FirstNReducer implements Reducer {
    private Integer maxAmount;
    private ArrayList<Record> groupRecord;


    public FirstNReducer(int maxAmount) {
        this.maxAmount = maxAmount;
        groupRecord = new ArrayList<Record>(maxAmount);
    }

    @Override
    public void apply(Record inputRecord, Collector collector, Map<String, Object> groupByEntries) {
        Record copy = inputRecord.copy();
        if (groupRecord.size() < maxAmount) {
            groupRecord.add(copy);
        }
    }


    @Override
    public void signalGroupWasFinished(Collector collector, Map<String, Object> groupByEntries) {
        groupRecord.stream().forEach(rec -> collector.collect(rec));
        groupRecord.clear();
    }
}
