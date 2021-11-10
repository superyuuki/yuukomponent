package com.superyuuki.yuukomponent.api.config.behavior.error;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

import java.util.Arrays;

public class NoSuchBehaviorValueFailure extends BadConfigFailure {


    public NoSuchBehaviorValueFailure(String component, String[] value) {
        super(String.format("A component definition with the id: %s is missing behavior value(s): %s", component, Arrays.toString(value)));
    }

    @Override
    public String solution() {
        return "Add configuration value(s) for the offending component definition.";
    }
}
