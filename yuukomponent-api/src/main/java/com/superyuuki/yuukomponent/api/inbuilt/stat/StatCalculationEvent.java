package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * Adapter
 * @param <T>
 */
public class StatCalculationEvent<T> implements Event {

    private final AtomicReference<T> objectReference;

    public StatCalculationEvent(T defaultValue) {
        this.objectReference = new AtomicReference<>(defaultValue);
    }

    public void modify(UnaryOperator<T> operator) {
        objectReference.updateAndGet(operator);
    }

    public T result() {
        return objectReference.get();
    }


}
