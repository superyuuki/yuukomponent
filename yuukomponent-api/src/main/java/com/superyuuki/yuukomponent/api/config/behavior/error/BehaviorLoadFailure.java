package com.superyuuki.yuukomponent.api.config.behavior.error;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

public abstract class BehaviorLoadFailure extends BadConfigFailure {
    public BehaviorLoadFailure(String message) {
        super(message);
    }
}
