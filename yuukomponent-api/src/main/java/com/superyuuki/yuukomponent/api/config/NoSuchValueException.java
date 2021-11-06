package com.superyuuki.yuukomponent.api.config;

public class NoSuchValueException extends RuntimeException {

    public NoSuchValueException(String message) {
        super("No such configuration value exists for key: " + message);
    }
}
