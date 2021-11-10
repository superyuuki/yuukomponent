package com.superyuuki.yuukomponent.api.addon.error;

public class DependencyFailure extends StartupFailure {
    public DependencyFailure(String plugin, String missing) {
        super(String.format("The plugin: %s was missing dependency: %s", plugin, missing));
    }

    @Override
    public String solution() {
        return "Install the missing addon and restart YuuKomponent";
    }
}
