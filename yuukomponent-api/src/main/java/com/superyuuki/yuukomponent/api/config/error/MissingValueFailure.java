package com.superyuuki.yuukomponent.api.config.error;

public class MissingValueFailure extends BadConfigFailure {
    public MissingValueFailure(String file, String key) {
        super(String.format("Configuration file: %s is missing a value for key: %s", file, key));
    }

    @Override
    public String solution() {
        return "Add the value to your configuration. Values typically are the second part of a configuration pair attached to a key, where 'key: value'.";
    }
}
