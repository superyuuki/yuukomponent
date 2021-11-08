package com.superyuuki.yuukomponent.api.addon.internal;

import com.superyuuki.yuukomponent.api.addon.Addon;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;

public interface AddonLoader {

    Addon load() throws StartupFailure;
    Description description();

}
