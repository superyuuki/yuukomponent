package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.addons.error.NoAddonDependencyFailure;

import java.util.Collection;

public interface AddonLimitedAccess {

    <T extends Addon> T getAddon(String addonName) throws NoAddonDependencyFailure;
    <T extends Addon> T getAddon(Class<T> addonClass) throws NoAddonDependencyFailure;
    <T extends Addon> Collection<T> getAllAddons(Class<T> addonClass);

    boolean hasAddon(String pluginName);
    <T extends Addon> boolean hasAddon(Class<T> pluginClass);

}
