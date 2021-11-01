package com.superyuuki.yuukomponent.core.inbuilt.stat.object;

import com.superyuuki.yuukomponent.api.inbuilt.stat.StatCorrectness;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class ScListing implements StatCorrectness {

    private final AtomicBoolean correct = new AtomicBoolean(true);
    private volatile String value;

    @Override
    public void fail(String string) {
        if (correct.compareAndSet(true, false)) {
            value = string;
        }
    }

    @Override
    public boolean isCorrect() {
        return correct.get();
    }

    @Override
    public Optional<String> missingKey() {
        return Optional.ofNullable(value);
    }
}
