package com.superyuuki.yuukomponent.core.behavior.example;

import com.superyuuki.yuukomponent.api.Behavior;
import com.superyuuki.yuukomponent.api.BehaviorLoader;

import java.util.UUID;

public class SomeBehaviorLoader implements BehaviorLoader {
    @Override
    public Behavior instantiate(UUID uuid) {
        return null;
    }
}
