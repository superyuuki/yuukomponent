package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.config.NoSuchBehaviorException;

import java.util.UUID;

public interface BehaviorRegistry {

    BehaviorLoader load(String behaviorIdentifier) throws NoSuchBehaviorException;

}
