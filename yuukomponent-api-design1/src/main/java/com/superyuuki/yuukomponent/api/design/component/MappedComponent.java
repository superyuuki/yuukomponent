package com.superyuuki.yuukomponent.api.design.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.design.Behavior;
import com.superyuuki.yuukomponent.api.design.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

//bad, component has no uuid assigned to itself
public class MappedComponent implements Component {

    private final Behavior behavior;
    private final Map<UUID, Component> children;

    public MappedComponent(Behavior behavior, Map<UUID, Component> children) {
        this.behavior = behavior;
        this.children = children;
    }

    @Override
    public void handle(Event event) {

        behavior.handle(event);

        for (Component component : children.values()) {
            component.handle(event);
        }

    }

    @Override
    public boolean present(UUID uuid) {
        return false;
    }

    //TODO optimize this
    @Override
    public Optional<Component> search(UUID uuid) {
        Component child;

        if ((child = children.get(uuid)) != null) return Optional.of(child);

        for (Component component : children.values()) {
            Optional<Component> possibleValue = component.search(uuid);

            if (possibleValue.isPresent()) {
                return possibleValue;
            }
        }

        return Optional.empty();
    }


}
