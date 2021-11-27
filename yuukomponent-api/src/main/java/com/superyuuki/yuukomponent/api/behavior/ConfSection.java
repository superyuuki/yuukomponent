package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;

/**
 * Section of configuration dedicated to a configuration section's held data
 *
 * named confsection to avoid collision with spigewt
 */
public interface ConfSection {

    <T> T value(String key, Class<T> clazz) throws NoSuchBehaviorValueFailure, WrongTypeValueFailure;

}
