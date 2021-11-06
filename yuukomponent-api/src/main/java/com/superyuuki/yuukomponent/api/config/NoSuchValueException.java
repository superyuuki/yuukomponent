package com.superyuuki.yuukomponent.api.config;

public class NoSuchValueException extends BadConfigFailure {

    public NoSuchValueException(String message) {
        super("No such configuration value exists for key: " + message);
    }
}
