package com.superyuuki.yuukomponent.api.exception;

public class UncheckedFailure extends RuntimeException {

    public UncheckedFailure(YuuKomponentFailure cause) {
        super(cause);
    }
}
