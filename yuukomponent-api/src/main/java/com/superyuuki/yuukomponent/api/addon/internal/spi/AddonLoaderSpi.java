package com.superyuuki.yuukomponent.api.addon.internal.spi;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;

public interface AddonLoaderSpi {

    AddonLoader[] provide(Platform platform) throws StartupFailure;

}
