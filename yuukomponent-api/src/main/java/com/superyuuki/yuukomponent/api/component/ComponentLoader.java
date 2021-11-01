package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;

import java.util.Collection;
import java.util.UUID;

/**
 * Like an event.
 * We create them and send them to a config storage input bus to create components ;)
 */
public interface ComponentLoader {

    Component load(UUID reservedId);

    //impregnate a component
    Component loadWithChildren(UUID reservedId, Collection<Component> forcedChildren);

}
