package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.StartupFailure;

public class NoSuchAddonFeatureFailure extends StartupFailure {
    public NoSuchAddonFeatureFailure(Class<?> clazz) {
        super(String.format("A feature with type: %s was queried for but no such feature could be located!", clazz.getCanonicalName()));
    }

    @Override
    public String solution() {
        return "Install an addon that provides the above feature and restart YuuKomponent";
    }
}
