package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class NoSuchBehaviorException extends YuuKomponentException {

    public NoSuchBehaviorException() {
    }

    public NoSuchBehaviorException(String message) {
        super(message);
    }

    public NoSuchBehaviorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBehaviorException(Throwable cause) {
        super(cause);
    }

    public NoSuchBehaviorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
