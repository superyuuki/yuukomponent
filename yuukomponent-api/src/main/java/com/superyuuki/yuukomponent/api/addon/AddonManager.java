package com.superyuuki.yuukomponent.api.addon;

import java.util.Optional;

public interface AddonManager {

    <T extends Feature> T feature(Class<T> feature);
    <T extends Feature> void register(Class<T> feature, Feature instance);

}
