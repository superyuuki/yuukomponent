package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonDriver;
import com.superyuuki.yuukomponent.api.addons.AddonManager;
import com.superyuuki.yuukomponent.api.addons.AddonSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualityAddonDriver implements AddonDriver {

    private final Platform platform;
    private final AddonSource source;
    private final Validator validator;

    public QualityAddonDriver(Platform platform, AddonSource source, Validator validator) {
        this.platform = platform;
        this.source = source;
        this.validator = validator;
    }

    @Override
    public AddonManager startup() throws StartupFailure {

        Class<?>[] allClasses = source.load();

        List<Class<? extends Addon>> toFilter = new ArrayList<>();

        for (Class<?> clazz : allClasses) {
            validator.translate(clazz).ifPresent(toFilter::add);
        }

        Map<String, Addon> toReturn = new HashMap<>();

        for (Node node : new BasicResolver(toFilter, validator).resolve()) {
            toReturn.put(node.name(), node.construct());
        }

        AddonManager manager = new QualityAddonManager(Map.copyOf(toReturn)); //immutable is good

        for (Map.Entry<String, Addon> view : toReturn.entrySet()) {
            view.getValue().onStartup(new WrappedLimitedAccess(view.getKey(), manager, platform));
        }

        return manager;
    }

}
