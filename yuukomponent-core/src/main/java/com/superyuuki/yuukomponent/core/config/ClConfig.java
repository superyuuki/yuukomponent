package com.superyuuki.yuukomponent.core.config;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentStorage;
import com.superyuuki.yuukomponent.api.config.BehaviorLoader;
import com.superyuuki.yuukomponent.api.config.ComponentLoader;
import com.superyuuki.yuukomponent.api.config.data.DataSection;
import com.superyuuki.yuukomponent.core.behavior.CombinantBehavior;
import com.superyuuki.yuukomponent.core.component.MappedComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class ClConfig implements ComponentLoader {

    private final ComponentStorage storage;

    private final DataSection valueDefs;
    private final List<BehaviorLoader> behaviorDefs;
    //TODO slot inclusion

    public ClConfig(ComponentStorage storage, DataSection valueDefs, List<BehaviorLoader> behaviorDefs) {
        this.storage = storage;
        this.valueDefs = valueDefs;
        this.behaviorDefs = behaviorDefs;
    }

    @Override
    public Component load() {
        return loadWithChildren(new ArrayList<>());
    }

    @Override
    public Component loadWithChildren(Collection<Component> forcedChildren) {
        //TODO should this go elsewhere?
        UUID uuid = UUID.randomUUID();

        while (storage.present(uuid)) {
            //This id is already present. This either represents sheer lottery-style chance collision, or more likely,
            //forced collision due to some stupid server owner making a copy of an item manually and not through the api.
            //We still support stupid server owners, so shift to a new uuid until we are able to insert this id to the container.

            uuid = UUID.randomUUID();
        }

        List<Behavior> make = new ArrayList<>();

        for (BehaviorLoader behavior : behaviorDefs) {
            make.add(behavior.instantiate(uuid, valueDefs));
        }

        return new MappedComponent(uuid, new CombinantBehavior(make), forcedChildren);
    }
}
