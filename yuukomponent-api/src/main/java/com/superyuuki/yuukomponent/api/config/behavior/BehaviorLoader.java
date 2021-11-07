package com.superyuuki.yuukomponent.api.config.behavior;

import com.superyuuki.yuukomponent.api.blueprint.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

/**
 * Configuration-specific.
 *
 * Creates behavior sources out of config data
 */
public interface BehaviorLoader {

    BehaviorSource load(DataSection section, EventDispatcher dispatcher) throws WrongTypeValueFailure, NoSuchBehaviorValueFailure;

}