package com.superyuuki.yuukomponent.core.stats;

import com.superyuuki.yuukomponent.api.trait.Event;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * Adapter
 * @param <T>
 */
public class StatCalculationEvent<T> implements Event {

    private final AtomicReference<T> objectReference;
    private final String identifier;
    private final Class<T> clazz;

    public StatCalculationEvent(T defaultValue, String identifier, Class<T> clazz) {
        this.objectReference = new AtomicReference<>(defaultValue);
        this.identifier = identifier;
        this.clazz = clazz;
    }

    public void modify(UnaryOperator<T> operator) {
        objectReference.updateAndGet(operator);
    }

    public T result() {
        return objectReference.get();
    }


}
