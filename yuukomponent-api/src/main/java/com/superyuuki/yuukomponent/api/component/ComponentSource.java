package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

/**
 * Like an event.
 * We create them and send them to a config storage input bus to create components ;)
 */
public interface ComponentSource extends Function<UUID, Component> {

    Component load(UUID uuid);

    //impregnate a component
    Component loadWithChildren(UUID uuid, Collection<Component> forcedChildren);

    @Override
    default Component apply(UUID uuid) {
        return load(uuid);
    }
}
