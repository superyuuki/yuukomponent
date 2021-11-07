package com.superyuuki.yuukomponent.core.inbuilt.stat.types;

import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatCalculationEvent;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.util.UUID;

public class IntegerStat implements Stat<Integer> {

    private final EventDispatcher dispatcher;
    private final UUID identifier;
    private final Integer baseInteger;

    public IntegerStat(EventDispatcher dispatcher, UUID identifier, Integer baseInteger) {
        this.dispatcher = dispatcher;
        this.identifier = identifier;
        this.baseInteger = baseInteger;
    }

    public IntegerStat(EventDispatcher dispatcher, UUID identifier) {
        this.dispatcher = dispatcher;
        this.identifier = identifier;
        this.baseInteger = 0;
    }

    @Override
    public Integer stat() {
        StatCalculationEvent<Integer> calculationEvent = new StatCalculationEvent<>(baseInteger);

        dispatcher.dispatchChildInclusive(calculationEvent, identifier);

        return calculationEvent.result();
    }
}
