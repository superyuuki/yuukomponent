package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public abstract class StartupFailure extends YuuKomponentFailure {

    public StartupFailure(String message) {
        super(message);
    }

    public StartupFailure(Throwable cause) {
        super(cause);
    }
}
