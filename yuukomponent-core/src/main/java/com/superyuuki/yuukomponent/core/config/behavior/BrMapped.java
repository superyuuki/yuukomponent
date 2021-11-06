package com.superyuuki.yuukomponent.core.config.behavior;

import com.superyuuki.yuukomponent.api.config.BehaviorLoader;
import com.superyuuki.yuukomponent.api.config.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.NoSuchBehaviorException;

import java.util.Map;

/**
 * Behavior registry
 */
public class BrMapped implements BehaviorRegistry {

    private final Map<String, BehaviorLoader> map;

    public BrMapped(Map<String, BehaviorLoader> map) {
        this.map = map;
    }

    @Override
    public BehaviorLoader loader(String behaviorIdentifier) throws NoSuchBehaviorException {

        if (map.containsKey(behaviorIdentifier)) return map.get(behaviorIdentifier);

        throw new NoSuchBehaviorException(behaviorIdentifier);
    }
}
