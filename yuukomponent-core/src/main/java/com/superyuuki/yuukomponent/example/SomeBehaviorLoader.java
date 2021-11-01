package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorLoader;

import java.util.UUID;

public class SomeBehaviorLoader implements BehaviorLoader {
    @Override
    public Behavior instantiate(UUID uuid) {
        return null;
    }
}
