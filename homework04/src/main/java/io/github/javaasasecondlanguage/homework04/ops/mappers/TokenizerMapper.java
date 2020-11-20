package io.github.javaasasecondlanguage.homework04.ops.mappers;

import io.github.javaasasecondlanguage.homework04.Collector;
import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.ops.Mapper;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.List.of;

/**
 * Splits text in the specified column into words, then creates a new record with each word.
 *
 * Split should happen on the following symbols: " ", ".", ",", "!", ";", "?", "'", ":"
 */
public class TokenizerMapper implements Mapper {

    private static final String SPLIT_PATTERN = "[\\s,\\.\\!\\;\\?\\'\\:\"]+";
    private String inputColumn;
    private String outputColumn;

    public TokenizerMapper(String inputColumn, String outputColumn) {
        this.inputColumn = inputColumn;
        this.outputColumn = outputColumn;
    }

    @Override
    public void apply(Record inputRecord, Collector collector) {
        Record copy = inputRecord.copy();

        String tokenizedText = (String) copy.getData().get(inputColumn);

        Map<String, Object> withoutTokenizedText = copy.getData();
        withoutTokenizedText.remove(inputColumn);

        String[] words = tokenizedText.split(SPLIT_PATTERN);

        for (String word : words) {
            Map<String, Object> data = withoutTokenizedText;
            data.put(outputColumn, word);
            Record record = new Record(data);
            collector.collect(record);
        }
    }
}
