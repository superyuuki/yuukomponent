package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Collection;

/**
 * Like an event.
 * We create them and send them to a config storage input bus to create components ;)
 */
public interface ComponentSource {

    Component load();

    //impregnate a component
    Component loadWithChildren(Collection<Component> forcedChildren);

}
