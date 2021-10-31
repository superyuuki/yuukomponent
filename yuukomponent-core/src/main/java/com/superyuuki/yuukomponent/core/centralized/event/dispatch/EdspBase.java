package com.superyuuki.yuukomponent.core.centralized.event.dispatch;

import com.github.benmanes.caffeine.cache.Cache;
import com.superyuuki.yuukomponent.api.Event;
import com.superyuuki.yuukomponent.api.Component;
import com.superyuuki.yuukomponent.core.centralized.ComponentStorage;
import com.superyuuki.yuukomponent.core.centralized.event.EventDispatcher;

import java.util.Optional;
import java.util.UUID;

public class EdspBase implements EventDispatcher {

    private final ComponentStorage storage;
    private final Cache<UUID, Component> subcomponentCache;

    public EdspBase(ComponentStorage storage, Cache<UUID, Component> subcomponentCache) {
        this.storage = storage;
        this.subcomponentCache = subcomponentCache;
    }

    @Override
    public boolean dispatchChildInclusive(Event event, UUID uuid) {

        Optional<Component> surfaceLevel = storage.retrieveOpt(uuid);

        if (surfaceLevel.isPresent()) {
            surfaceLevel.get().handle(event);
            return true;
        }

        Component nullableChild = subcomponentCache.get(uuid, id -> {
            for (Component component : storage.components()) {
                if (component.present(id)) {
                    return component;
                }
            }

            return null;
        });

        if (nullableChild != null) {
            nullableChild.handle(event);
            return true;
        }

        return false;
    }
}
