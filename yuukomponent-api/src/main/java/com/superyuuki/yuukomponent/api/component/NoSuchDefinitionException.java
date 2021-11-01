package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

/**
 * Exception thrown when no component definition exists in configuration with a specific identifier
 */
public class NoSuchDefinitionException extends YuuKomponentException {

    public NoSuchDefinitionException() {
    }

    public NoSuchDefinitionException(String message) {
        super(message);
    }

    public NoSuchDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchDefinitionException(Throwable cause) {
        super(cause);
    }

    public NoSuchDefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
