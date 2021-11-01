package com.superyuuki.yuukomponent.api.component.config;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class BadConfigException extends YuuKomponentException {

    public BadConfigException() {
    }

    public BadConfigException(String message) {
        super(message);
    }

    public BadConfigException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadConfigException(Throwable cause) {
        super(cause);
    }

    public BadConfigException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
