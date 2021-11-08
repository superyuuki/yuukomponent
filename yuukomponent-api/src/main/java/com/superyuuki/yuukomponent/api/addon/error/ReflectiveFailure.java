package com.superyuuki.yuukomponent.api.addon.error;

public class ReflectiveFailure extends StartupFailure {

    public ReflectiveFailure(String name, Exception e) {
        super(String.format("A reflective exception occurred while trying to load plugin: %s with exception: %s", name, e));
    }

    @Override
    public String solution() {
        return "This indicates a code error in the addons you installed. Please copy the above exception text (red) and report the issue to our support including the entire error.";
    }
}
