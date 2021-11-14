package com.superyuuki.yuukomponent.core.addon.internal;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.api.addon.internal.AddonRepository;

public class ServiceLoaderAddonRepository implements AddonRepository {
    @Override
    public AddonLoader[] provide(Platform platform) throws StartupFailure {
        return new AddonLoader[0];
    }
}
