package com.superyuuki.yuukomponent.api.addon;

import java.util.Optional;

public interface AddonManager {

    <T extends Feature> Optional<T> feature(Class<T> feature);

}
