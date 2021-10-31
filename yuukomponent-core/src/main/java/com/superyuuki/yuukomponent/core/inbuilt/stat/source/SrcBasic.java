package com.superyuuki.yuukomponent.core.inbuilt.stat.source;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.core.centralized.event.EventDispatcher;
import com.superyuuki.yuukomponent.core.inbuilt.stat.StatSource;
import com.superyuuki.yuukomponent.core.inbuilt.stat.stat.StBasic;

import java.util.UUID;

public class SrcBasic implements StatSource {

    private final UUID uuid;
    private final EventDispatcher driver;

    public SrcBasic(UUID uuid, EventDispatcher driver) {
        this.uuid = uuid;
        this.driver = driver;
    }

    @Override
    public <T> Stat<T> make(Key<T> key) {
        return new StBasic<>(driver, uuid, key);
    }
}
