package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonManager;
import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFailure;
import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFeatureFailure;
import com.superyuuki.yuukomponent.api.addons.error.TooManyImplementingAddonsFailure;
import com.superyuuki.yuukomponent.api.addons.error.UnexpectedAddonTypeFailure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicAddonManager implements AddonManager {

    private final Map<String, Addon> map;

    public BasicAddonManager(Map<String, Addon> map) {
        this.map = map;
    }


    @Override
    public <T extends Addon> T getAddon(Class<T> addonClass, String addonName) throws NoSuchAddonFailure, UnexpectedAddonTypeFailure {
        Addon addon = map.get(addonName);

        if (addon == null) throw new NoSuchAddonFailure(addonName);

        if (!addonClass.isInstance(addon)) throw new UnexpectedAddonTypeFailure(addonName, addonClass);

        return addonClass.cast(addon);
    }

    @Override
    public <T extends Addon> T getAddon(Class<T> addonClass) throws NoSuchAddonFeatureFailure, TooManyImplementingAddonsFailure {
        List<T> addons = getAllAddons(addonClass);

        if (addons.size() > 1) throw new NoSuchAddonFeatureFailure(addonClass);
        if (addons.size() < 1) throw new TooManyImplementingAddonsFailure(addonClass);

        return addons.get(0);
    }

    @Override
    public <T extends Addon> List<T> getAllAddons(Class<T> addonClass) {
        List<T> addons = new ArrayList<>();

        for (Addon addon : map.values()) {
            if (addonClass.isInstance(addon)) {
                addons.add(addonClass.cast(addon));
            }
        }

        return addons;
    }

    @Override
    public boolean addonExists(String pluginName) {
        return map.containsKey(pluginName);
    }

    @Override
    public <T extends Addon> boolean addonExists(Class<T> pluginClass) {
        for (Addon addon : map.values()) {
            if (pluginClass.isInstance(addon)) return true;
        }

        return false;
    }

    @Override
    public void shutdown() {
        for (Addon addon : map.values()) {
            addon.onShutdown();
        }
    }
}
