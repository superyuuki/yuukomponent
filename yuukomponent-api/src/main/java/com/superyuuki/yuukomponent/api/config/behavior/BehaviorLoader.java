package com.superyuuki.yuukomponent.api.config.behavior;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;

/**
 * Configuration-specific.
 *
 * Creates behavior sources out of config data
 */
public interface BehaviorLoader {

    BehaviorSource load(DataSection section, EventDispatcher dispatcher) throws WrongTypeValueFailure, NoSuchBehaviorValueFailure;

}
