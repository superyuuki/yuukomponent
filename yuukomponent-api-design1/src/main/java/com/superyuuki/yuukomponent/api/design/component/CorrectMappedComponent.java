package com.superyuuki.yuukomponent.api.design.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.design.Behavior;
import com.superyuuki.yuukomponent.api.design.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class CorrectMappedComponent implements Component {

    private final UUID identifier;
    private final Behavior behavior;
    private final Collection<Component> children;

    public CorrectMappedComponent(UUID identifier, Behavior behavior, Collection<Component> children) {
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
        return Optional.empty();
    }
}
