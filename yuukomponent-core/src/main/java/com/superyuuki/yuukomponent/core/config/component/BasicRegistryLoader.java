package com.superyuuki.yuukomponent.core.config.component;

import com.superyuuki.yuukomponent.api.NoSuchBehaviorException;
import com.superyuuki.yuukomponent.api.component.UUIDProvider;
import com.superyuuki.yuukomponent.api.config.*;
import com.superyuuki.yuukomponent.api.config.data.ConfigSection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicRegistryLoader implements RegistryLoader {

    private final Map<String, ConfigSection> parsedOutput;

    private final UUIDProvider provider;
    private final BehaviorRegistry registry;

    public BasicRegistryLoader(Map<String, ConfigSection> parsedOutput, UUIDProvider provider, BehaviorRegistry registry) {
        this.parsedOutput = parsedOutput;
        this.provider = provider;
        this.registry = registry;
    }

    @Override
    public ComponentRegistry load() throws NoSuchBehaviorException {

        Map<String, ComponentLoader> componentMap = new HashMap<>();

        for (Map.Entry<String, ConfigSection> sectionEntry : parsedOutput.entrySet()) {
            String identifier = sectionEntry.getKey();
            ConfigSection section = sectionEntry.getValue();

            List<BehaviorLoader> behaviors = new ArrayList<>();

            for (String id : section.behaviors()) {
                behaviors.add(registry.loader(id));
            }

            ComponentLoader loader = new ConfigComponentLoader(provider, section.data(), behaviors);
            componentMap.put(identifier, loader);
        }


        return new MappedComponentRegistry(componentMap);
    }
}
