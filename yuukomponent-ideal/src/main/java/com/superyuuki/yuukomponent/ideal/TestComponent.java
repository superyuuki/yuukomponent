package com.superyuuki.yuukomponent.ideal;

import com.superyuuki.yuukomponent.ideal.standard.StandardBehavior;
import com.superyuuki.yuukomponent.ideal.standard.StandardComponent;

import java.util.Map;

public class TestComponent implements StandardComponent {

    private final CompStats compStats;
    private final StandardBehavior standardBehavior;
    private final Map<String, StandardComponent> children;

    public TestComponent(CompStats compStats, StandardBehavior standardBehavior, Map<String, StandardComponent> children) {
        this.compStats = compStats;
        this.standardBehavior = standardBehavior;
        this.children = children;
    }

    @Override
    public void handle(Object event) {
        StatObject object = new EmptyObject();

        stats(object);

        standardBehavior.behavior(event, object);

        for (StandardComponent child : children.values()) {
            child.handle(event, object);
        }
    }

    @Override
    public void handle(Object event, StatObject stats) {
        standardBehavior.behavior(event, stats);

        for (StandardComponent child : children.values()) {
            child.handle(event, stats);
        }
    }


    @Override
    public void stats(StatObject initialStats) {

        compStats.modify(initialStats);

        for (StandardComponent standardComponent : children.values()) {
            standardComponent.stats(initialStats);
        }

    }

}
