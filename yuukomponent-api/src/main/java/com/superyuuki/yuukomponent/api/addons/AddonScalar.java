package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFeatureFailure;
import com.superyuuki.yuukomponent.api.addons.error.TooManyImplementingAddonsFailure;

public interface AddonScalar<T extends Addon> {

    void loadTo(AddonManager manager) throws NoSuchAddonFeatureFailure, TooManyImplementingAddonsFailure;
    T retrieve();

}
