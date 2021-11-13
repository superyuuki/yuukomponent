package com.superyuuki.yuukomponent.core.event;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentDispatcher;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class GuavaSubcomponentCache implements SubcomponentCache {

    private final ComponentDispatcher storage;
    private final Cache<UUID, Component> subcomponentCache = CacheBuilder.newBuilder()
            .expireAfterAccess(Duration.ofMinutes(2)) //subcomponents will be searched for again after 2 minutes
            .build();

    public GuavaSubcomponentCache(ComponentDispatcher storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Component> get(UUID uuid) throws CacheExecutionFailure {
        try {
            return Optional.of(subcomponentCache.get(uuid, () -> {
                for (Component component : storage.components()) {
                    if (component.present(uuid)) {
                        return component;
                    }
                }

                throw new NoKeyFailure(uuid.toString());
            }));
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NoKeyFailure) {
                return Optional.empty();
            }

            throw new CacheExecutionFailure(uuid, e.getCause());
        }
    }
}
