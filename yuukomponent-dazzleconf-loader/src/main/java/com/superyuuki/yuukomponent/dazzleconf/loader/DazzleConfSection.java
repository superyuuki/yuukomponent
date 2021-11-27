package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.ConfSection;
import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;
import space.arim.dazzleconf.serialiser.FlexibleType;

import java.util.Map;

public class DazzleConfSection implements ConfSection {

    private final String componentId;
    private final Map<String, FlexibleType> section;

    public DazzleConfSection(String componentId, Map<String, FlexibleType> section) {
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
