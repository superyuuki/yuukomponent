package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.AddonContext;
import com.superyuuki.yuukomponent.api.addons.AddonLimitedAccess;
import com.superyuuki.yuukomponent.api.behavior.BehaviorPool;
import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.behavior.error.BadConfigFailure;

@AddonContext(
        name = "core-dazzle-registry",
        version = "0.0.1-SNAPSHOT",
        platformVersion = "0.0.1-SNAPSHOT",
        dependencies = {}
)
public class DazzleRegistryEntrypoint implements RegistryEntrypoint {

    @Override
    public void onStartup(AddonLimitedAccess manager) throws StartupFailure {

    }

    @Override
    public BehaviorRegistry load(BehaviorPool behaviorPool) throws BadConfigFailure {
        return null;
    }
}
