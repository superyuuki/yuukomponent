package com.superyuuki.yuukomponent.ideal;

import com.superyuuki.yuukomponent.ideal.so.EmptyObject;

import java.util.Map;

public class TestComponent implements StatComponent {

    private final CompStats compStats;
    private final CompBehavior compBehavior;
    private final Map<String, StatComponent> children;

    public TestComponent(CompStats compStats, CompBehavior compBehavior, Map<String, StatComponent> children) {
        this.compStats = compStats;
        this.compBehavior = compBehavior;
        this.children = children;
    }

    @Override
    public void handle(Object event) {
        var stats = stats();

        for (StatComponent child : children.values()) {
            child.handle(event, stats);
        }
    }

    @Override
    public void handle(Object event, StatObject stats) {
        compBehavior.doSomething(event, stats);

        for (StatComponent child : children.values()) {
            child.handle(event, stats);
        }
    }

    @Override
    public StatObject stats() {

        StatObject initial = new EmptyObject();

        compStats.modify(initial);

        for (StatComponent statComponent : children.values()) {
            statComponent.stats(initial);
        }

        return initial;
    }

    @Override
    public StatObject stats(StatObject initialStats) {

        compStats.modify(initialStats);

        for (StatComponent statComponent : children.values()) {
            compStats.modify(initialStats);
        }

        return initialStats;

    }

}
