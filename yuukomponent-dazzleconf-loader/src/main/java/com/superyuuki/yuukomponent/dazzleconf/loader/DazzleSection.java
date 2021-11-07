package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.behavior.DataSection;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;

public class DazzleSection implements DataSection {
    @Override
    public <T> T value(String key, Class<T> clazz) throws NoSuchBehaviorValueFailure, WrongTypeValueFailure {
        return null;
    }
}
