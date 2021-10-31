package com.superyuuki.yuukomponent.api;

/**
 * Parent exception
 */
public class YuuKomponentException extends RuntimeException {

    public YuuKomponentException() {
    }

    public YuuKomponentException(String message) {
        super(message);
    }

    public YuuKomponentException(String message, Throwable cause) {
        super(message, cause);
    }

    public YuuKomponentException(Throwable cause) {
        super(cause);
    }

    public YuuKomponentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
