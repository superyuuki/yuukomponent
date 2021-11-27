package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

import java.util.Map;

public class DazzleBehaviorRegistry implements BehaviorRegistry {

    private final Map<String, ComponentSource> sourceMap;

    public DazzleBehaviorRegistry(Map<String, ComponentSource> sourceMap) {
        this.sourceMap = sourceMap;
    }

    @Override
    public Component createOfType(String componentIdentifier) throws NoSuchDefinitionException {

        if (!sourceMap.containsKey(componentIdentifier)) throw new NoSuchDefinitionException(componentIdentifier);

        return sourceMap.get(componentIdentifier).make();
    }
}
