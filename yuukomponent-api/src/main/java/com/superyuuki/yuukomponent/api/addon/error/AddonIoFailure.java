package com.superyuuki.yuukomponent.api.addon.error;

public class AddonIoFailure extends StartupFailure {

    public AddonIoFailure(String file, Exception e) {
        super(String.format("An IO exception occurred loading your addon: %s with exception: %s", file, e));
    }

    @Override
    public String solution() {
        return "This indicates a code error in the addons you installed. Please copy the above exception text (red) and report the issue to our support including the entire error.";
    }
}
