package com.superyuuki.yuukomponent.bootstrap.component.low;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CaffeineCompPool implements CompPool {

    private final CompDriver driver;
    private final Cache<UUID, Behavior> cache;

    public CaffeineCompPool(CompDriver driver, Duration duration) {
        this.driver = driver;
        this.cache = Caffeine.newBuilder().expireAfterAccess(duration).build();
    }

    @Override
    public Behavior get(UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException {
        return cache.get(uuid, driver::get);
    }

    @Override
    public boolean present(UUID uuid) {
        return driver.present(uuid); //accuracy!!!!!1
    }

    @Override
    public boolean delete(UUID uuid) {

        boolean result = driver.delete(uuid);

        if (result) {
            cache.invalidate(uuid);
        }

        return result;
    }

    @Override
    public Optional<Behavior> getCached(UUID uuid) {
        return Optional.ofNullable(cache.getIfPresent(uuid));
    }

    @Override
    public List<Behavior> getCached(List<UUID> uuids) {

        for (UUID uuid) {
            cache.getAllPresent()
        }

        return null;
    }


}
