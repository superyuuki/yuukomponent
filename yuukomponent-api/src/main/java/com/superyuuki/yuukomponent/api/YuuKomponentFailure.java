package com.superyuuki.yuukomponent.api;

public abstract class YuuKomponentFailure extends Exception {

    public YuuKomponentFailure() {
    }

    public YuuKomponentFailure(String message) {
        super(message);
    }

    public YuuKomponentFailure(String message, Throwable cause) {
        super(message, cause);
    }

    public YuuKomponentFailure(Throwable cause) {
        super(cause);
    }

    public YuuKomponentFailure(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
