package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.exception.Failures;

public class BadAddonClassFailure extends StartupFailure {

    private final Throwable cause;

    public BadAddonClassFailure(Throwable cause) {
        super("Something went wrong loading one of your addons: Threw exception ClassNotFoundException");

        this.cause = cause;
    }

    @Override
    public String solution() {
        return Failures.SUPPORT + "One of the addon files is corrupted or missing internal parts." + Failures.COPY_RED;
    }

    @Override
    public boolean critical() {
        return true;
    }

    @Override
    public Throwable wrapped() {
        return cause;
    }
}
