package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorLoader;
import com.superyuuki.yuukomponent.api.plugin.NexusRegistry;

import java.util.UUID;

public class SomeBehaviorLoader implements BehaviorLoader {

    private final NexusRegistry nexusRegistry;

    public SomeBehaviorLoader(NexusRegistry nexusRegistry) {
        this.nexusRegistry = nexusRegistry;
    }

    @Override
    public Behavior instantiate(UUID uuid) {

        nexusRegistry.retrieve(StatRegi);

        return null;
    }
}
