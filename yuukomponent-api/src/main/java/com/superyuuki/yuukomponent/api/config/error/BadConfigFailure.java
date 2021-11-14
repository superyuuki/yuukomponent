package com.superyuuki.yuukomponent.api.config.error;

import com.superyuuki.yuukomponent.api.StartupFailure;

public abstract class BadConfigFailure extends StartupFailure {



    public BadConfigFailure(String message) {
        super(message);
    }

    public BadConfigFailure(Throwable cause) {
        super(cause);
    }



}
