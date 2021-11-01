package com.superyuuki.yuukomponent.core.behavior.registry;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorLoader;
import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.behavior.NoSuchBehaviorException;

import java.util.Map;
import java.util.UUID;

public class MappedRegistry implements BehaviorRegistry {

    private final Map<String, BehaviorLoader> loaderMap;

    public MappedRegistry(Map<String, BehaviorLoader> loaderMap) {
        this.loaderMap = loaderMap;
    }

    @Override
    public Behavior load(UUID uuid, String behaviorIdentifier) throws NoSuchBehaviorException {

        BehaviorLoader loader = loaderMap.get(behaviorIdentifier);

        return null;
    }
}
