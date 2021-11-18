package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.addons.Addon;

import java.util.Map;

public interface BehaviorEntrypoint extends Addon {

    Map<String, BehaviorSource> generateSources();

}
