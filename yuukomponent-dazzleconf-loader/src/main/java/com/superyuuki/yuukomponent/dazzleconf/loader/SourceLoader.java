package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.component.ComponentSource;
import com.superyuuki.yuukomponent.api.behavior.ConfSection;
import com.superyuuki.yuukomponent.dazzleconf.loader.error.NoSuchBehaviorFailure;
import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.util.*;

public class SourceLoader {

    private final Map<String, ConfigurationSection> sectionMap;
    private final BehaviorRegistry registry;
    private final EventDispatcher dispatcher;
    private final UUIDProvider uuidProvider;

    public SourceLoader(Map<String, ConfigurationSection> sectionMap, BehaviorRegistry registry, EventDispatcher dispatcher, UUIDProvider uuidProvider) {
        this.sectionMap = sectionMap;
        this.registry = registry;
        this.dispatcher = dispatcher;
        this.uuidProvider = uuidProvider;
    }

    Map<String, ComponentSource> load() throws NoSuchBehaviorFailure, WrongTypeValueFailure, NoSuchBehaviorValueFailure {
        Map<String, ComponentSource> map = new HashMap<>();

        for (Map.Entry<String, ConfigurationSection> sectionEntry : sectionMap.entrySet()) {
            String componentId = sectionEntry.getKey();
            ConfigurationSection section = sectionEntry.getValue();

            List<BehaviorSource> behaviors = new ArrayList<>();

            ConfSection confSection = new DazzleConfSection(componentId, section.values());

            for (String behaviorId : section.behaviors()) {
                Optional<BehaviorLoader> loader = registry.loader(behaviorId);

                if (loader.isEmpty()) throw new NoSuchBehaviorFailure(componentId, behaviorId);

                behaviors.add(loader.get().load(confSection, dispatcher));
            }

            ComponentSource loader = new MappedComponentSource(uuidProvider, behaviors);
            map.put(componentId, loader);
        }

        return map;
    }

}
