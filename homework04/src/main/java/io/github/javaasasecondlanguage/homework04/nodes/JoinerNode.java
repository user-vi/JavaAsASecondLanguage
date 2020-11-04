package io.github.javaasasecondlanguage.homework04.nodes;

import io.github.javaasasecondlanguage.homework04.Record;
import io.github.javaasasecondlanguage.homework04.RoutingCollector;

import java.util.List;

public class JoinerNode implements ProcNode {

    public JoinerNode(List<String> keyColumns) {
        throw new IllegalStateException("You must implement this");
    }

    @Override
    public RoutingCollector getCollector() {
        throw new IllegalStateException("You must implement this");
    }

    @Override
    public void push(Record inputRecord, int gateNumber) {
        throw new IllegalStateException("You must implement this");
    }
}
