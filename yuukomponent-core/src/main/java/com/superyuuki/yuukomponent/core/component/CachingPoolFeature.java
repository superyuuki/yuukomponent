package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentPoolFeature;
import com.superyuuki.yuukomponent.api.transactional.Transaction;

import java.time.Duration;
import java.util.UUID;

public class CachingPoolFeature implements ComponentPoolFeature {

    private final Cache<UUID, Component> cache;
    private final ComponentPoolFeature delegate;

    public CachingPoolFeature(ComponentPoolFeature delegate, Duration expiry) {
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
