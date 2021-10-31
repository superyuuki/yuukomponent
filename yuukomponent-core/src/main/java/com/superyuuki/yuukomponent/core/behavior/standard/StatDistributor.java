package com.superyuuki.yuukomponent.core.behavior.standard;

import com.superyuuki.yuukomponent.api.Event;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;
import com.superyuuki.yuukomponent.api.Behavior;

import java.util.List;

public class StatDistributor implements Behavior {

    private final StatBehavior statBehavior;
    private final List<Behavior> typedBehaviors;

    public StatDistributor(StatBehavior statBehavior, List<Behavior> typedBehaviors) {
        this.statBehavior = statBehavior;
        this.typedBehaviors = typedBehaviors;
    }

    @Override
    public void handle(Event event) {
        if (event instanceof StatObject) {
            statBehavior.handle((StatObject) event);
        } else {
            for (Behavior behavior : typedBehaviors) {
                behavior.handle(event);
            }
        }
    }
}
