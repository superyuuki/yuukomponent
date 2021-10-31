package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class StatNotPresentException extends YuuKomponentException {

    public StatNotPresentException() {
    }

    public StatNotPresentException(String message) {
        super(message);
    }

    public StatNotPresentException(String message, Throwable cause) {
        super(message, cause);
    }

    public StatNotPresentException(Throwable cause) {
        super(cause);
    }

    public StatNotPresentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
