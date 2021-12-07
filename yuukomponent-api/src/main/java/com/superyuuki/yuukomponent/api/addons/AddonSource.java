package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

import java.util.Map;

public interface AddonSource {

    Map<String, Addon> load() throws StartupFailure;
}
