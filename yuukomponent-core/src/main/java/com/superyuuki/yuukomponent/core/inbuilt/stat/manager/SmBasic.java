package com.superyuuki.yuukomponent.core.inbuilt.stat.manager;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.core.inbuilt.stat.object.NullObject;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;
import com.superyuuki.yuukomponent.core.centralized.event.EventDispatcher;
import com.superyuuki.yuukomponent.core.inbuilt.stat.StatManager;
import com.superyuuki.yuukomponent.core.inbuilt.stat.StatSource;

import java.util.UUID;

public class SmBasic implements StatManager {

    private final EventDispatcher driver;

    public SmBasic(EventDispatcher driver) {
        this.driver = driver;
    }

    @Override
    public <T> Stat<T> retrieve(UUID uuid, Key<T> key) {
        return () -> {
            StatObject object = new NullObject();

            driver.dispatchChildInclusive(object, uuid);

            return (T) object.value(key.identifier());
        };
    }

    @Override
    public StatSource retrieveFor(UUID uuid) {
        return new StatSource() {
            @Override
            public <T> Stat<T> make(Key<T> key) {
                StatObject object = new NullObject();

                driver.dispatchChildInclusive(object, uuid);

                return (T) object.value(key.identifier());
            }
        }
    }
}
