package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.ComponentRegistry;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Map;

public class DazzleComponentRegistry implements ComponentRegistry {

    private final Map<String, ComponentSource> sourceMap;

    public DazzleComponentRegistry(Map<String, ComponentSource> sourceMap) {
        this.sourceMap = sourceMap;
    }

    @Override
    public Component createOfType(String componentIdentifier) throws NoSuchDefinitionException {

        if (!sourceMap.containsKey(componentIdentifier)) throw new NoSuchDefinitionException(componentIdentifier);

        return sourceMap.get(componentIdentifier).make();
    }
}
