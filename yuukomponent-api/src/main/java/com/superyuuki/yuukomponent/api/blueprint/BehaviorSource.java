package com.superyuuki.yuukomponent.api.blueprint;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.config.behavior.error.BehaviorLoadFailure;

import java.util.UUID;

/**
 * Creates behaviors
 */
public interface BehaviorSource {

    Behavior instantiate(UUID id);

}
