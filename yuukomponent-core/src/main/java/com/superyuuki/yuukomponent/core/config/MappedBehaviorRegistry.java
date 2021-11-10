package com.superyuuki.yuukomponent.core.config;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.BehaviorRegistry;

import java.util.Map;
import java.util.Optional;

/**
 * Behavior registry
 *
 * Why does this exist...
 */
public class MappedBehaviorRegistry implements BehaviorRegistry {

    private final Map<String, BehaviorSource> map;

    public MappedBehaviorRegistry(Map<String, BehaviorSource> map) {
        this.map = map;
    }

    @Override
    public Optional<BehaviorSource> loader(String behaviorIdentifier) {
        return Optional.ofNullable(map.get(behaviorIdentifier));
    }
}
