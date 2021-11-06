package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.config.BehaviorLoader;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.core.behavior.TypedBehavior;
import com.superyuuki.yuukomponent.core.event.EventDispatcher;
import com.superyuuki.yuukomponent.core.inbuilt.stat.key.GenericKey;
import com.superyuuki.yuukomponent.core.inbuilt.stat.stat.StBasic;

import java.util.UUID;

public class SomeBehaviorLoader implements BehaviorLoader {

    public static final Key<Integer> GENERIC_DAMAGE = new GenericKey<>("generic_damage", Integer.class);
    public static final Key<Integer> GENERIC_RANGE = new GenericKey<>("generic_range", Integer.class);

    private final EventDispatcher dispatcher;

    public SomeBehaviorLoader(EventDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public Behavior instantiate(UUID uuid) {

        Stat<Integer> damage = new StBasic<>(dispatcher, uuid, GENERIC_DAMAGE);
        Stat<Integer> range = new StBasic<>(dispatcher, uuid, GENERIC_RANGE);

        return new TypedBehavior.Adapter<>(new SomeBehavior(damage, range), SomeBehavior.Event.class);
    }
}
