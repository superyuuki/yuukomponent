package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.api.behavior.TypedBehavior;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;
import com.superyuuki.yuukomponent.stat.types.IntegerStat;

import java.util.UUID;

public class SomeBehaviorSource implements BehaviorSource {

    private final int someConfigValue;
    private final int defaultDamage; //must start at..
    private final EventDispatcher dispatcher;

    public SomeBehaviorSource(int someConfigValue, int defaultDamage, EventDispatcher dispatcher) {
        this.someConfigValue = someConfigValue;
        this.defaultDamage = defaultDamage;
        this.dispatcher = dispatcher;
    }

    @Override
    public Behavior instantiate(UUID uuid) {

        Stat<Integer> damage = new IntegerStat(dispatcher, uuid, key, 0); //calculations start at 0
        Stat<Integer> range = new IntegerStat(dispatcher, uuid, key, defaultDamage); //range starts off as 5 if called by this behavior
        //e.g. if a weapon with base 0 modifiers +2, x5, +5 would give you 2x5+5 = 15, this would give you 7x5+5 = 40 for this behavior calc only

        return new TypedBehavior.Adapter<>(new SomeBehavior(someConfigValue, damage, range), SomeBehavior.Event.class);
    }
}
