package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.addons.Addon;

public class NoAddonDependencyFailure extends AddonValidityFailure {
    public NoAddonDependencyFailure(String dependent, String message) {
        super(String.format("Addon: %s requires an addon named: %s but could not find any implementation!", dependent, message));
    }

    public NoAddonDependencyFailure(String dependent, Class<? extends Addon> clazz) {
        super(String.format("Addon: %s requires an addon implementing feature: %s but could not find any!", dependent, clazz.getCanonicalName()));
    }

    @Override
    public String solution() {
        return "Install a addon that implements the required addon feature and restart yuukomponent";
    }
}
