package com.superyuuki.yuukomponent.core.addon.error;

import com.superyuuki.yuukomponent.api.StartupFailure;

public class DuplicateExportFailure extends StartupFailure {
    public DuplicateExportFailure(String plugin1, String plugin2, String feature) {
        super(String.format("Plugins: %s and %s both are trying to export the same feature: %s", plugin1, plugin2, feature));
    }

    @Override
    public String solution() {
        return "Features are meant to be unique and only one can be provided. Remove one of the plugins and restart YuuKomponent.";
    }
}
