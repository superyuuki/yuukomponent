package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.newtype.CompDriver;
import com.superyuuki.yuukomponent.api.component.newtype.CompPool;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class CaffeineCompPool implements CompPool {

    private final CompDriver driver;
    private final FactoryOfTheFuture factory;
    private final AsyncCache<UUID, Behavior> asyncCache;

    public CaffeineCompPool(CompDriver driver, FactoryOfTheFuture factory, Duration duration) {
        this.driver = driver;
        this.factory = factory;
        this.asyncCache = Caffeine.newBuilder().expireAfterAccess(duration).buildAsync();
    }

    @Override
    public CentralisedFuture<Behavior> get(UUID uuid) {
        return (CentralisedFuture<Behavior>) asyncCache.get(uuid, (uuid1, executor) -> factory.supplyAsync(() -> driver.get(uuid1)));
    }

    @Override
    public CentralisedFuture<Boolean> present(UUID uuid) {
        return factory.supplyAsync(() -> driver.present(uuid));
    }

    @Override
    public CentralisedFuture<Boolean> delete(UUID uuid) {
        return factory.supplyAsync(() -> driver.delete(uuid)).thenApply(bool -> {
            if (bool) {
                asyncCache.synchronous().invalidate(uuid);
            }

            return bool;
        });
    }
}
