package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentSource;
import com.superyuuki.yuukomponent.api.component.UUIDProvider;
import com.superyuuki.yuukomponent.core.behavior.CombinantBehavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class MappedComponentSource implements ComponentSource {

    private final UUIDProvider provider;
    private final List<BehaviorSource> sources;

    public MappedComponentSource(UUIDProvider provider, List<BehaviorSource> sources) {
        this.provider = provider;
        this.sources = sources;
    }

    @Override
    public Component load() {
        return loadWithChildren(new ArrayList<>());
    }

    @Override
    public Component loadWithChildren(Collection<Component> forcedChildren) {
        UUID uuid = provider.provide();


        List<Behavior> behaviors = new ArrayList<>();

        for (BehaviorSource source : sources) {
            behaviors.add(source.instantiate(uuid));
        }

        return new MappedComponent(uuid, new CombinantBehavior(behaviors), forcedChildren);
    }
}
