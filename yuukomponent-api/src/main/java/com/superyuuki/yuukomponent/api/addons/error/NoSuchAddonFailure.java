package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.StartupFailure;

public class NoSuchAddonFailure extends StartupFailure {
    public NoSuchAddonFailure(String name) {
        super(String.format("No such addon exists with name: %s", name));
    }

    @Override
    public String solution() {
        return "Install the addon with above name and restart YuuKomponent";
    }
}
