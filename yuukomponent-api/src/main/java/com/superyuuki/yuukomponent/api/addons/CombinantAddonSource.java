package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

import java.util.List;
import java.util.Map;

public class CombinantAddonSource implements AddonSource {

    private final List<AddonSource> sources;

    public CombinantAddonSource(List<AddonSource> sources) {
        this.sources = sources;
    }

    @Override
    public Map<String, Addon> load() throws StartupFailure {
        return null; //TODO impl
    }
}
