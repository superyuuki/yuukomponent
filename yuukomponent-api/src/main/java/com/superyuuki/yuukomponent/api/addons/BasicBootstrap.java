package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.impl.BasicAddonManager;
import com.superyuuki.yuukomponent.api.addons.impl.WrappedLimitedAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BasicBootstrap implements Bootstrap {

    private final List<AddonSource> sources = new ArrayList<>();

    private final Platform platform;

    public BasicBootstrap(Platform platform) {
        this.platform = platform;
    }

    @Override
    public void register(AddonSource source) {

    }

    @Override
    public AddonManager launch() throws StartupFailure {
        Map<String, Addon> addons = new CombinantAddonSource(sources).load();

        AddonManager manager = new BasicAddonManager(addons); //immutable is good

        for (Map.Entry<String, Addon> view : addons.entrySet()) {
            view.getValue().onStartup(new WrappedLimitedAccess(view.getKey(), manager, platform));
        }

        return manager;
    }
}
