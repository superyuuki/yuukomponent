package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentPool;
import com.superyuuki.yuukomponent.api.component.Transaction;

import java.time.Duration;
import java.util.UUID;

public class CachingPool implements ComponentPool {

    private final Cache<UUID, Component> cache;
    private final ComponentPool delegate;

    public CachingPool(ComponentPool delegate, Duration expiry) {
        this.cache = Caffeine.newBuilder().expireAfterAccess(expiry).build();
        this.delegate = delegate;
    }


    @Override
    public Component get(Transaction tx, UUID uuid) {
        return cache.get(uuid, id -> delegate.get(tx, uuid));
    }

    @Override
    public void delete(Transaction tx, UUID uuid) {
        delegate.delete(tx, uuid);
    }
}
