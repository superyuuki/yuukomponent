package com.superyuuki.yuukomponent.api.addon;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;

/**
 * Represents a small addon meant to provide features from within the application
 */
public interface Addon {

    void onStartup(Platform platform) throws StartupFailure;
    void onShutdown();

}
