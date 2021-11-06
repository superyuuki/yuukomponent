package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.config.BadConfigException;

public class DuplicateBehaviorException extends BadConfigException {

    public DuplicateBehaviorException(String message) {
        super("You cannot have two exceptions with the same key: " + message);
    }
}
