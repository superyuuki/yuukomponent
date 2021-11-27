package com.superyuuki.yuukomponent.api.exception;

public abstract class YuuKomponentFailure extends Exception implements Solvable {

    public YuuKomponentFailure(Throwable cause) {
        super(cause);
    }

    public YuuKomponentFailure(String message) {
        super(message);
    }

}
