package com.superyuuki.yuukomponent.api.addons;

import java.util.concurrent.atomic.AtomicBoolean;

//Threadsafety makes my brain pee
public class BasicScalar<T> implements Scalar<T> {

    final AtomicBoolean accessed = new AtomicBoolean(false);

    volatile T value;

    @Override
    public void load(T value) {
        if (accessed.compareAndSet(false, true)) {
            this.value = value;
        }

        throw new IllegalStateException("Scalar loaded twice, illegal operation");
    }

    @Override
    public T retrieve() {
        if (value == null) throw new IllegalStateException("Scalar not loaded");

        return value;
    }
}
