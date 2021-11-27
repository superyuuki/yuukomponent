package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.exception.Failures;

public class BadAddonIOFailure extends StartupFailure {

    private final Throwable throwable;

    public BadAddonIOFailure(Throwable cause) {
        super("Something went wrong loading one of your addons: Threw exception IOException");

        this.throwable = cause;
    }

    @Override
    public String solution() {
        return Failures.SUPPORT + "One of your addons had a file corruption or YuuKomponent had a file loading error." + Failures.COPY_RED;
    }

    @Override
    public boolean critical() {
        return true;
    }

    @Override
    public Throwable wrapped() {
        return throwable;
    }
}
