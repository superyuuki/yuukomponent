package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.addon.Addon;
import com.superyuuki.yuukomponent.api.addon.AddonContext;
import com.superyuuki.yuukomponent.api.addon.AddonDescription;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.config.ConfigurationLoaderFeature;

@AddonDescription(
        displayName = "yuukomponent-core-dazzleconf-loader",
        version = "0.0.1-SNAPSHOT",
        description = "DazzleConf binding for YuuKomponent",
        author = "Yuuki",
        exports = ConfigurationLoaderFeature.class
)
public class DazzleConfAddonContext implements Addon {
    @Override
    public void onStartup(AddonContext context) throws StartupFailure {

    }

    @Override
    public void onShutdown() {

    }
}
