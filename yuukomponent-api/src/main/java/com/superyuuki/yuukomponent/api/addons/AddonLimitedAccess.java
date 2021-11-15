package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addons.error.NoAddonDependencyFailure;
import com.superyuuki.yuukomponent.api.addons.error.TooManyImplementingAddonsFailure;
import com.superyuuki.yuukomponent.api.addons.error.UnexpectedAddonTypeFailure;

import java.nio.file.Path;
import java.util.Collection;

public interface AddonLimitedAccess {

    Path dataFolder();
    Platform platform();

    <T extends Addon> T getAddon(Class<T> addonClass, String addonName) throws NoAddonDependencyFailure, UnexpectedAddonTypeFailure;
    <T extends Addon> T getAddon(Class<T> addonClass) throws NoAddonDependencyFailure, TooManyImplementingAddonsFailure;
    <T extends Addon> Collection<T> getAllAddons(Class<T> addonClass);

    boolean hasAddon(String pluginName);
    <T extends Addon> boolean hasAddon(Class<T> pluginClass);

}
