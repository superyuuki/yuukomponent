package com.superyuuki.yuukomponent.api.design.centralized;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.design.Component;
import com.superyuuki.yuukomponent.api.design.centralized.EventMan;
import com.superyuuki.yuukomponent.api.design.centralized.InsertionMan;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ComponentManager implements EventMan, InsertionMan {

    private final Map<UUID, Component> componentMap = new ConcurrentHashMap<>();

    @Override
    public void event(Event event) {
        for (Component component : componentMap.values()) {
            component.handle(event);
        }
    }

    @Override
    public void event(UUID component, Event event) {
        componentMap.get(component).handle(event);
    }

    @Override
    public void register(Component component) {

    }

}
