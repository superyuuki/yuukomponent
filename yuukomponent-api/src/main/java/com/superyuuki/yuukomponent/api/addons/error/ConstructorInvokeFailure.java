package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.exception.Failures;

public class ConstructorInvokeFailure extends AddonValidityFailure {

    private final Throwable throwable;

    public ConstructorInvokeFailure(Class<?> owner, Throwable message) {
        super(String.format("Something went wrong trying to instantiate addon: %s with error: %s", owner, message));

        this.throwable = message;
    }

    @Override
    public String solution() {
        return Failures.SUPPORT +
                "This is a critical bug indicating that the reflection performed to create a new instance of your addon has failed."
                + Failures.COPY_RED;
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
