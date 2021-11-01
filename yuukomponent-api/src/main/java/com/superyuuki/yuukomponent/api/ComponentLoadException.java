package com.superyuuki.yuukomponent.api;

public class ComponentLoadException extends YuuKomponentException {

    public ComponentLoadException() {
    }

    public ComponentLoadException(String message) {
        super(message);
    }

    public ComponentLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComponentLoadException(Throwable cause) {
        super(cause);
    }

    public ComponentLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
