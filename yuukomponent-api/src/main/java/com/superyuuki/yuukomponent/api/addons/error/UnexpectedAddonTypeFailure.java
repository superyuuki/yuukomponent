package com.superyuuki.yuukomponent.api.addons.error;

public class UnexpectedAddonTypeFailure extends AddonValidityFailure {
    public UnexpectedAddonTypeFailure(String message, Class<?> clazz) {
        super(String.format("The addon: %s was found but was not of queried type: %s", message, clazz));
    }

    @Override
    public String solution() {
        return "Report this to the developer of said addon.";
    }
}
