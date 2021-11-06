package com.superyuuki.yuukomponent.core.config.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.UUIDProvider;
import com.superyuuki.yuukomponent.api.config.BehaviorLoader;
import com.superyuuki.yuukomponent.api.config.ComponentLoader;
import com.superyuuki.yuukomponent.api.config.data.DataSection;
import com.superyuuki.yuukomponent.core.behavior.CombinantBehavior;
import com.superyuuki.yuukomponent.core.component.MappedComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ConfigComponentLoader implements ComponentLoader {

    private final UUIDProvider provider;

    private final DataSection valueDefs;
    private final List<BehaviorLoader> behaviorDefs;

    //TODO slot inclusion
    public ConfigComponentLoader(UUIDProvider provider, DataSection valueDefs, List<BehaviorLoader> behaviorDefs) {
        this.provider = provider;
        this.valueDefs = valueDefs;
        this.behaviorDefs = behaviorDefs;
    }


    @Override
    public Component load() {
        return loadWithChildren(new ArrayList<>());
    }

    @Override
    public Component loadWithChildren(Collection<Component> forcedChildren) {
        UUID uuid = provider.provide();

        List<Behavior> make = new ArrayList<>();

        for (BehaviorLoader behavior : behaviorDefs) {
            make.add(behavior.instantiate(uuid, valueDefs));
        }

        return new MappedComponent(uuid, new CombinantBehavior(make), forcedChildren);
    }
}
