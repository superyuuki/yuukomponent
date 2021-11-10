package com.superyuuki.yuukomponent.api.addon.error;

public class InvalidAddonFileFailure extends StartupFailure {
    public InvalidAddonFileFailure(String file) {
        super(String.format("The file: %s in your addons folder is not a YuuKomponent addon! Please remove it.", file));
    }

    @Override
    public String solution() {
        return null;
    }
}
