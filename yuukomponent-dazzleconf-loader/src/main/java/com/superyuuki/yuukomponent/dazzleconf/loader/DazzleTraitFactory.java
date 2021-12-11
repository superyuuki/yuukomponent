package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.trait.TraitFactory;

import java.util.Map;

public class DazzleTraitFactory implements TraitFactory {

    private final Map<String, ComponentSource> sourceMap;

    public DazzleTraitFactory(Map<String, ComponentSource> sourceMap) {
        this.sourceMap = sourceMap;
    }

    @Override
    public Component createOfType(String componentIdentifier) throws NoSuchTraitException {

        if (!sourceMap.containsKey(componentIdentifier)) throw new NoSuchTraitException(componentIdentifier);

        return sourceMap.get(componentIdentifier).make();
    }
}
