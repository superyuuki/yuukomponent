package com.superyuuki.yuukomponent.core.config.component;

import com.superyuuki.yuukomponent.api.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.config.ComponentLoader;
import com.superyuuki.yuukomponent.api.config.ComponentRegistry;

import java.util.Map;

public class MappedComponentRegistry implements ComponentRegistry {

    private final Map<String, ComponentLoader> loaderMap;

    public MappedComponentRegistry(Map<String, ComponentLoader> loaderMap) {
        this.loaderMap = loaderMap;
    }

    @Override
    public ComponentLoader loader(String id) throws NoSuchDefinitionException {
        if (loaderMap.containsKey(id)) return loaderMap.get(id);

        throw new NoSuchDefinitionException(id);
    }
}
