package com.superyuuki.yuukomponent.core.event;

import com.google.common.cache.Cache;
import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentExecutionFailure;
import com.superyuuki.yuukomponent.api.component.ComponentPool;
import com.superyuuki.yuukomponent.api.component.error.CacheExecutionFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BasicDispatcher implements EventDispatcher {

    private final ComponentPool storage;

    //TODO Make sure EventDispatcher's child linking queue is updated when components are moved between slots
    private final Cache<UUID, Component> subcomponentCache;

    public BasicDispatcher(ComponentPool storage, Cache<UUID, Component> subcomponentCache) {
        this.storage = storage;
        this.subcomponentCache = subcomponentCache;
    }

    @Override
    public boolean dispatchChildInclusive(Event event, UUID uuid) throws ComponentExecutionFailure {

        Optional<Component> surfaceLevel = storage.retrieveOpt(uuid);

        if (surfaceLevel.isPresent()) {
            surfaceLevel.get().handle(event);
            return true;
        }

        try {
            Component nullableChild = subcomponentCache.get(uuid, () -> {
                for (Component component : storage.components()) {
                    if (component.present(uuid)) {
                        return component;
                    }
                }

                throw new NoKeyFailure(uuid.toString());
            });

            nullableChild.handle(event);

            return true;
        } catch (ExecutionException e) {
            if (e.getCause() instanceof NoKeyFailure) {
                return false;
            }

            throw new CacheExecutionFailure(uuid, e.getCause());
        }

    }
}
