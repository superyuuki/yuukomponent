package com.superyuuki.yuukomponent.api.behavior.error;

public class BadConfigIOFailure extends BadConfigFailure {

    public BadConfigIOFailure(Throwable cause) {
        super(cause);
    }

    @Override
    public String solution() {
        return null;
    }
}
