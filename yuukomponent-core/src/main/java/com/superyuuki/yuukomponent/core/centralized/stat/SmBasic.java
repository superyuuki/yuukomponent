package com.superyuuki.yuukomponent.core.centralized.stat;

import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;
import com.superyuuki.yuukomponent.api.keyed.stat.map.NullObject;
import com.superyuuki.yuukomponent.api.keyed.stat.map.StatObject;
import com.superyuuki.yuukomponent.core.centralized.IntelligentDriver;
import com.superyuuki.yuukomponent.core.centralized.StatManager;

import java.util.UUID;

public class SmBasic implements StatManager {

    private final IntelligentDriver driver;

    public SmBasic(IntelligentDriver driver) {
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
}
