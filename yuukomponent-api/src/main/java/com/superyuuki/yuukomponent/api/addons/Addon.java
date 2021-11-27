package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

public interface Addon {

    default void onStartup(AddonLimitedAccess manager) throws StartupFailure {};
    default void onShutdown() {};

}
