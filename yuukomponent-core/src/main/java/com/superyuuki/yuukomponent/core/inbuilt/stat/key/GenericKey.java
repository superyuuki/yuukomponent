package com.superyuuki.yuukomponent.core.inbuilt.stat.key;

import com.superyuuki.yuukomponent.api.inbuilt.stat.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatNotPresentException;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;

public class GenericKey<T> implements Key<T> {

    private final String identifier;
    private final Class<T> clazz;

    public GenericKey(String identifier, Class<T> clazz) {
        this.identifier = identifier;
        this.clazz = clazz;
    }

    @Override
    public T get(StatObject object) throws StatNotPresentException {
        Object ob = object.value(identifier);

        if (ob == null) throw new StatNotPresentException("No value present for stat: " + identifier);

        return clazz.cast(ob);
    }
}
