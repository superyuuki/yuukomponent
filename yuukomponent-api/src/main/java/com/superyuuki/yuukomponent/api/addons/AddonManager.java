package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFailure;
import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFeatureFailure;
import com.superyuuki.yuukomponent.api.addons.error.TooManyImplementingAddonsFailure;
import com.superyuuki.yuukomponent.api.addons.error.UnexpectedAddonTypeFailure;

import java.util.List;

public interface AddonManager {

    <T extends Addon> T getAddon(Class<T> addonClass, String addonName) throws NoSuchAddonFailure, UnexpectedAddonTypeFailure;
    <T extends Addon> T getAddon(Class<T> addonClass) throws NoSuchAddonFeatureFailure, TooManyImplementingAddonsFailure;
    <T extends Addon> List<T> getAllAddons(Class<T> addonClass);

    <T extends Addon> void addAddon(String addonName, Class<T> addonType, T addon);

    boolean addonExists(String pluginName);
    <T extends Addon> boolean addonExists(Class<T> pluginClass);

    void shutdown();


}
