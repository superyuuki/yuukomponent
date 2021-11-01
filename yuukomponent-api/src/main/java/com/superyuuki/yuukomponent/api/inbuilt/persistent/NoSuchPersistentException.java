package com.superyuuki.yuukomponent.api.inbuilt.persistent;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class NoSuchPersistentException extends YuuKomponentException {

    public NoSuchPersistentException() {
    }

    public NoSuchPersistentException(String message) {
        super(message);
    }

    public NoSuchPersistentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchPersistentException(Throwable cause) {
        super(cause);
    }

    public NoSuchPersistentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
