package com.superyuuki.yuukomponent.api.config;

public class DuplicateBehaviorFailure extends BadConfigFailure {

    public DuplicateBehaviorFailure(String message) {
        super("You cannot have two exceptions with the same key: " + message);
    }
}
