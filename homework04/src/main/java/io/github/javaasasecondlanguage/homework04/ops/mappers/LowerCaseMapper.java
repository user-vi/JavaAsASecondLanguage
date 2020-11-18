package io.github.javaasasecondlanguage.homework04.ops.mappers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Mapper;

/**
 * Shifts selected column to lowercase.
 */
public class LowerCaseMapper implements Mapper {
    String columnName;

    public LowerCaseMapper(String column) {
        this.columnName = column;
    }

    @Override
    public void apply(Record inputRecord, Collector collector) {
        Record copy = inputRecord.copy();
        copy.set(columnName, ((String) inputRecord.get(columnName)).toLowerCase());

        collector.collect(copy);
    }
}
