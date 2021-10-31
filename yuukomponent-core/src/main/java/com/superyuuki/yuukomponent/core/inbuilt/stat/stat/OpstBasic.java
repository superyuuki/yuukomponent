package com.superyuuki.yuukomponent.core.inbuilt.stat.stat;

import com.superyuuki.yuukomponent.api.inbuilt.OptionalKey;
import com.superyuuki.yuukomponent.api.inbuilt.stat.OptionalStat;
import com.superyuuki.yuukomponent.core.centralized.event.EventDispatcher;

import java.util.Optional;
import java.util.UUID;

public class OpstBasic<T> implements OptionalStat<T> {

    private final EventDispatcher driver;
    private final UUID uuid;
    private final OptionalKey<T> key;

    public OpstBasic(EventDispatcher driver, UUID uuid, OptionalKey<T> key) {
        this.driver = driver;
        this.uuid = uuid;
        this.key = key;
    }

    @Override
    public Optional<T> stat() {
        return Optional.empty();
    }

    @Override
    public T orDefault() {
        return null;
    }
}
