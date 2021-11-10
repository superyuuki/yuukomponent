package com.superyuuki.yuukomponent.stat.types;

import com.superyuuki.yuukomponent.stat.Stat;
import com.superyuuki.yuukomponent.stat.StatCalculationEvent;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.util.UUID;

public class IntegerStat implements Stat<Integer> {

    private final EventDispatcher dispatcher;
    private final UUID identifier;
    private final String key;
    private final Integer baseInteger;

    public IntegerStat(EventDispatcher dispatcher, UUID identifier, String key, Integer baseInteger) {
        this.dispatcher = dispatcher;
        this.identifier = identifier;
        this.key = key;
        this.baseInteger = baseInteger;
    }

    public IntegerStat(EventDispatcher dispatcher, UUID identifier, String key) {
        this.dispatcher = dispatcher;
        this.identifier = identifier;
        this.baseInteger = 0;
        this.key = key;
    }

    @Override
    public Integer stat() {
        StatCalculationEvent<Integer> calculationEvent = new StatCalculationEvent<>(baseInteger, key, Integer.class);

        dispatcher.dispatchChildInclusive(calculationEvent, identifier);

        return calculationEvent.result();
    }
}
