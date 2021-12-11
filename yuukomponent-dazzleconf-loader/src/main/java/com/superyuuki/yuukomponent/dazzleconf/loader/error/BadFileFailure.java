package com.superyuuki.yuukomponent.dazzleconf.loader.error;

import com.superyuuki.yuukomponent.api.trait.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.exception.Failures;

public class BadFileFailure extends BadConfigFailure {

    private final Throwable t;

    public BadFileFailure(String configFile, Throwable message) {
        super(String.format("An IOException was thrown trying to read config: %s with exception: %s", configFile, message));

        this.t = this;
    }

    @Override
    public String solution() {
        return Failures.SUPPORT + "Your config file is corrupted. Please remake it and try again. Additionally," + Failures.COPY_RED;
    }

    @Override
    public boolean critical() {
        return true;
    }

    @Override
    public Throwable wrapped() {
        return t;
    }
}
