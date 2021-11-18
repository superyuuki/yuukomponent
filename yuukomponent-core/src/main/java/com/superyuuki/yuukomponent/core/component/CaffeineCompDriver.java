package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.newtype.CachedCompDriver;
import com.superyuuki.yuukomponent.api.component.newtype.CompDriver;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

public class CaffeineCompDriver implements CachedCompDriver {

    private final CompDriver driver;
    private final Cache<UUID, Component> cache;

    public CaffeineCompDriver(CompDriver driver, Duration duration) {
        this.driver = driver;
        this.cache = Caffeine.newBuilder().expireAfterAccess(duration).build();
    }

    @Override
    public Component get(UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException {
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
    public Optional<Component> getCached(UUID uuid) {
        return Optional.ofNullable(cache.getIfPresent(uuid));
    }
}
