package com.superyuuki.yuukomponent.api.addon.internal;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;

public interface AddonLoaderSpi {

    AddonLoader[] provide(Platform platform) throws StartupFailure;

}
