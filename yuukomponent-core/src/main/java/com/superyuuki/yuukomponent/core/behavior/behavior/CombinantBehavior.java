package com.superyuuki.yuukomponent.core.behavior.behavior;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.List;

public class CombinantBehavior implements Behavior {

    private final List<Behavior> behaviors;

    public CombinantBehavior(List<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    @Override
    public void handle(Event event) {
        for (Behavior behavior : behaviors) {
            behavior.handle(event);
        }
    }
}
