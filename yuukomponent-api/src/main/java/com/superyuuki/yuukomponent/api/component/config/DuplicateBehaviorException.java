package com.superyuuki.yuukomponent.api.component.config;

public class DuplicateBehaviorException extends BadConfigException {

    public DuplicateBehaviorException() {
    }

    public DuplicateBehaviorException(String message) {
        super(message);
    }

    public DuplicateBehaviorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateBehaviorException(Throwable cause) {
        super(cause);
    }

    public DuplicateBehaviorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
