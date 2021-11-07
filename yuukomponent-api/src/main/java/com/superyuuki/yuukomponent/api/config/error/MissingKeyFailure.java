package com.superyuuki.yuukomponent.api.config.error;

public class MissingKeyFailure extends BadConfigFailure {
    public MissingKeyFailure(String file, String key) {
        super(String.format("Configuration file: %s is missing a key: %s", file, key));
    }

    @Override
    public String solution() {
        return "Add the key to your configuration. Keys typically are the first part of a configuration pair, where 'key: value'.";
    }
}