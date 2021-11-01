package com.superyuuki.yuukomponent.api.component.config;

import com.superyuuki.yuukomponent.api.component.ComponentLoader;

import java.util.Collection;
import java.util.Map;

public interface ConfigSource {

    Map<String, ComponentLoader> load();

}
