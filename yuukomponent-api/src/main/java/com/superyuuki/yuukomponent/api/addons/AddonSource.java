package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;

public interface AddonSource {

    Class<?>[] load() throws StartupFailure;
}
