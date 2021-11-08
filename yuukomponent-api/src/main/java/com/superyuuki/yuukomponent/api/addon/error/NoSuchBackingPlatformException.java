package com.superyuuki.yuukomponent.api.addon.error;

import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;

public class NoSuchBackingPlatformException extends StartupFailure {


    public NoSuchBackingPlatformException(String addonName, String given, String expected) {
        super(String.format("Incorrect platform type: %s loading addon: %s, this platform only provides underlying platform: %s", given, addonName, expected));
    }

    @Override
    public String solution() {
        return "The addon you have provided was built for a different platform. Remove it and restart YuuKomponent.";
    }
}
