package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonLimitedAccess;
import com.superyuuki.yuukomponent.api.addons.AddonManager;
import com.superyuuki.yuukomponent.api.addons.error.*;

import java.nio.file.Path;
import java.util.Collection;

public class WrappedLimitedAccess implements AddonLimitedAccess {

    private final String name;
    private final AddonManager manager;
    private final Platform platform;

    public WrappedLimitedAccess(String name, AddonManager manager, Platform platform) {
        this.name = name;
        this.manager = manager;
        this.platform = platform;
    }


    @Override
    public Path dataFolder() {
        return null;
    }

    @Override
    public Platform platform() {
        return platform;
    }

    @Override
    public <T extends Addon> T getAddon(Class<T> addonClass, String addonName) throws NoAddonDependencyFailure, UnexpectedAddonTypeFailure {
        try {
            return manager.getAddon(addonClass, addonName);
        } catch (NoSuchAddonFailure e) {
            throw new NoAddonDependencyFailure(name, addonName);
        }
    }

    @Override
    public <T extends Addon> T getAddon(Class<T> addonClass) throws NoAddonDependencyFailure, TooManyImplementingAddonsFailure {
        try {
            return manager.getAddon(addonClass);
        } catch (NoSuchAddonFeatureFailure e) {
            throw new NoAddonDependencyFailure(name, addonClass);
        }
    }

    @Override
    public <T extends Addon> Collection<T> getAllAddons(Class<T> addonClass) {
        return manager.getAllAddons(addonClass);
    }

    @Override
    public boolean hasAddon(String pluginName) {
        return manager.addonExists(pluginName);
    }

    @Override
    public <T extends Addon> boolean hasAddon(Class<T> pluginClass) {
        return manager.addonExists(pluginClass);
    }
}
