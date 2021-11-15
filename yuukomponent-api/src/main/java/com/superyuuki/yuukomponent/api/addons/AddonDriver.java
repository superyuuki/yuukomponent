package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

public interface AddonDriver {

    AddonManager startup() throws StartupFailure;

}
