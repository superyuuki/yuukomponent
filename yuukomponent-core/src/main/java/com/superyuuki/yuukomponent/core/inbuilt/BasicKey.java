package com.superyuuki.yuukomponent.core.inbuilt;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;

public class BasicKey<T> implements Key<T> {

    private final String identifier;
    private final Class<T> clazz;

    public BasicKey(String identifier, Class<T> clazz) {
        this.identifier = identifier;
        this.clazz = clazz;
    }

    @Override
    public T get(StatObject object) {



        return null;
    }
}
