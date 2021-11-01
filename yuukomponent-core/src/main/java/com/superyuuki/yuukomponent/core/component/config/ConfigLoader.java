package com.superyuuki.yuukomponent.core.component.config;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentLoader;
import com.superyuuki.yuukomponent.core.behavior.behavior.CombinantBehavior;
import com.superyuuki.yuukomponent.core.component.component.MappedComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * Loads component from config
 *
 * @todo #2:120min need to implement slot loading from config
 */
public class ConfigLoader implements ComponentLoader {

    private final BehaviorRegistry behaviorRegistry;

    private final List<String> behaviors;

    public ConfigLoader(BehaviorRegistry behaviorRegistry, List<String> behaviors) {
        this.behaviorRegistry = behaviorRegistry;
        this.behaviors = behaviors;
    }


    @Override
    public Component load(UUID reservedId) {

        return loadWithChildren(reservedId, new ArrayList<>()); //new component. probably let the component's event system
        //handle the insertion of children
    }

    @Override
    public Component loadWithChildren(UUID reservedId, Collection<Component> forcedChildren) {

        List<Behavior> make = new ArrayList<>();

        for (String behaviorId : behaviors) {
            make.add(behaviorRegistry.load(reservedId, behaviorId));
        }

        return new MappedComponent(reservedId, new CombinantBehavior(make), forcedChildren);
    }
}
