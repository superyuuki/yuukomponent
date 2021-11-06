package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * @todo #2:30min add a hardcoded (sadly) slot change event handler class to the mappedcomponent, switch mappedcomponent
 * to actually use a map, reference map and slot change handler for slot modify events
 */
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
