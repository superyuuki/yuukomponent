package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.behavior.DataSection;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;

import java.util.Map;

public class DazzleSection implements DataSection {

    private final String componentId;
    private final Map<String, Object> section;

    public DazzleSection(String componentId, Map<String, Object> section) {
        this.componentId = componentId;
        this.section = section;
    }

    @Override
    public <T> T value(String key, Class<T> clazz) throws NoSuchBehaviorValueFailure, WrongTypeValueFailure {

        Object nullable = section.get(key);

        if (nullable == null) throw new NoSuchBehaviorValueFailure(componentId, new String[]{key});

        try {
            return clazz.cast(nullable);
        } catch (ClassCastException exception) {
            throw new WrongTypeValueFailure(componentId, key, clazz.getName(), nullable.getClass().getName());
        }

    }
}
