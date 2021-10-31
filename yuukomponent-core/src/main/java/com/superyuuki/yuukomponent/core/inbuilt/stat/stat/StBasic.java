package com.superyuuki.yuukomponent.core.inbuilt.stat.stat;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;
import com.superyuuki.yuukomponent.core.centralized.event.EventDispatcher;
import com.superyuuki.yuukomponent.core.inbuilt.stat.object.NullObject;

import java.util.UUID;

public class StBasic<T> implements Stat<T> {

    private final EventDispatcher driver;
    private final UUID uuid;
    private final Key<T> key;

    public StBasic(EventDispatcher driver, UUID uuid, Key<T> key) {
        this.driver = driver;
        this.uuid = uuid;
        this.key = key;
    }

    @Override
    public T stat() {
        StatObject object = new NullObject();

        driver.dispatchChildInclusive(object, uuid);

        return key.get(object);
    }
}
