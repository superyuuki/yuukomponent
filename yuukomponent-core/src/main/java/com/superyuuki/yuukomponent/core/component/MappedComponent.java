package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.Event;
import com.superyuuki.yuukomponent.api.Behavior;
import com.superyuuki.yuukomponent.api.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class MappedComponent implements Component {

    private final UUID identifier;
    private final Behavior behavior;
    private final Collection<Component> children;

    public MappedComponent(UUID identifier, Behavior behavior, Collection<Component> children) {
        this.identifier = identifier;
        this.behavior = behavior;
        this.children = children;
    }

    @Override
    public void handle(Event event) {
        behavior.handle(event);

        for (Component component : children) {
            component.handle(event);
        }
    }

    @Override
    public boolean present(UUID uuid) {
        if (uuid.equals(identifier)) return true; //stop, do not ask children.

        for (Component component : children) {
            if (component.present(uuid)) return true;
        }

        return false;
    }

    @Override
    public Optional<Component> search(UUID uuid) {
        if (uuid.equals(identifier)) return Optional.of(this);

        for (Component component : children) {
            Optional<Component> possible = component.search(uuid);

            if (possible.isPresent()) {
                return possible;
            }
        }

        return Optional.empty();
    }
}
