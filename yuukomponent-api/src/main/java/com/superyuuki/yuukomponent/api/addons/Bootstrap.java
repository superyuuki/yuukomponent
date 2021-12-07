package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

public interface Bootstrap {

    void register(AddonSource source);

    AddonManager launch() throws StartupFailure;

}
