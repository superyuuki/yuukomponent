package com.superyuuki.yuukomponent.api.addon.error;

public class NoSuchFeatureFailure extends StartupFailure {
    public NoSuchFeatureFailure(String addon, Class<?> message) {
        super(String.format("Addon: %s requires feature: %s to operate but was not provided by core or any other installed addon", addon , message.getCanonicalName()));
    }

    @Override
    public String solution() {
        return "Install a separate addon that provides the required feature to your existing addon and restart YuuKomponent";
    }
}
