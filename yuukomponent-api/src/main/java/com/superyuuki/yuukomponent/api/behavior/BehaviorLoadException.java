package com.superyuuki.yuukomponent.api.behavior;

public class BehaviorLoadException extends RuntimeException {

    public BehaviorLoadException() {
    }

    public BehaviorLoadException(String message) {
        super(message);
    }

    public BehaviorLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public BehaviorLoadException(Throwable cause) {
        super(cause);
    }

    public BehaviorLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
