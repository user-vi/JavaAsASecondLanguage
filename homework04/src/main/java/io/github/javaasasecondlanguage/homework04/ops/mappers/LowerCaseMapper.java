package io.github.javaasasecondlanguage.homework04.ops.mappers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Mapper;

/**
 * Shifts selected column to lowercase.
 */
public class LowerCaseMapper implements Mapper {

    public LowerCaseMapper(String column) {
        throw new IllegalStateException("You must implement this");
    }

    @Override
    public void apply(Record inputRecord, Collector collector) {
        throw new IllegalStateException("You must implement this");
    }
}
