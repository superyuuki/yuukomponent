package com.superyuuki.yuukomponent.api.addon.error;

public class BadAddonFailure extends StartupFailure {

    public BadAddonFailure(String name, Exception message) {
        super(String.format("Something went wrong loading your addon: %s. Exception was thrown: %s", name, message));
    }

    @Override
    public String solution() {
        return "This indicates a code error in the addons you installed. Please copy the above exception text (red) and report the issue to our support including the entire error.";
    }
}
