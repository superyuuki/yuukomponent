package com.superyuuki.yuukomponent.core.config;

import com.superyuuki.yuukomponent.api.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.ComponentSource;
import com.superyuuki.yuukomponent.api.config.ComponentRegistry;

import java.util.Map;

public class MappedComponentRegistry implements ComponentRegistry {

    private final Map<String, ComponentSource> loaderMap;

    public MappedComponentRegistry(Map<String, ComponentSource> loaderMap) {
        this.loaderMap = loaderMap;
    }

    @Override
    public ComponentSource loader(String id) throws NoSuchDefinitionException {
        if (loaderMap.containsKey(id)) return loaderMap.get(id);

        throw new NoSuchDefinitionException(id);
    }
}
