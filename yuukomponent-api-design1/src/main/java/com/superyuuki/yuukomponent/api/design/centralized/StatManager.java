package com.superyuuki.yuukomponent.api.design.centralized;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.design.centralized.EventMan;
import com.superyuuki.yuukomponent.api.design.centralized.StatMan;
import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;
import com.superyuuki.yuukomponent.api.keyed.stat.map.NullObject;
import com.superyuuki.yuukomponent.api.keyed.stat.map.StatObject;

import java.util.UUID;

public class StatManager implements StatMan {

    private final Cache<UUID, StatObject> cache = Caffeine.newBuilder().build(); //TODOLATER
    private final EventMan manOfTheEvent;

    public StatManager(EventMan manOfTheEvent) {
        this.manOfTheEvent = manOfTheEvent;
    }

    @Override
    public <T> Stat<T> make(UUID componentId, Key<T> key) {
        //this does not work since it will only get stats for the node position and below, and ignore parent stat modifiers.
        return () -> {
            var s = cache.get(componentId, uuid -> {
                StatObject object = new NullObject();

                manOfTheEvent.event(componentId, object);

                return object;
            });

            return (T) s.value(key.identifier());
        };
    }

}
