package com.superyuuki.yuukomponent.api;

public abstract class YuuKomponentFailure extends Exception {

    public YuuKomponentFailure(Throwable cause) {
        super(cause);
    }

    public YuuKomponentFailure(String message) {
        super(message);
    }

    public abstract String solution();

}
