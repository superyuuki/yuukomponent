package com.superyuuki.yuukomponent.api.config.behavior;

import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;

/**
 * Section of configuration dedicated to a configuration section's held data
 */
public interface DataSection {

    <T> T value(String key, Class<T> clazz) throws NoSuchBehaviorValueFailure, WrongTypeValueFailure;

}
