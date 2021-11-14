package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.internal.BasicResolver;
import com.superyuuki.yuukomponent.api.addons.internal.Validator;
import com.superyuuki.yuukomponent.api.addons.internal.depends.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class QualityAddonDriver implements AddonDriver {

    private final AddonSource source;
    private final Validator validator;

    public QualityAddonDriver(AddonSource source, Validator validator) {
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

        ConcurrentMap<String, Addon> toReturn = new ConcurrentHashMap<>();

        for (Node node : new BasicResolver(toFilter, validator).resolve()) {
            toReturn.put(node.name(), node.construct());
        }

        return new QualityAddonManager(toReturn);
    }

}
