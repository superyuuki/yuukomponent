package com.superyuuki.yuukomponent.core.component.config;

import com.superyuuki.yuukomponent.api.component.ComponentLoader;
import com.superyuuki.yuukomponent.api.component.config.ConfigSource;

import java.util.List;
import java.util.Map;

public class CombinantConfigSource implements ConfigSource {

    private final List<ConfigSource> sources;

    public CombinantConfigSource(List<ConfigSource> sources) {
        this.sources = sources;
    }

    @Override
    public Map<String, ComponentLoader> load() {

        return null;
    }
}
