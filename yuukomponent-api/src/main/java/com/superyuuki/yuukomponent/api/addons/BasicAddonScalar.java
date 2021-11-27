package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.addons.error.NoSuchAddonFeatureFailure;
import com.superyuuki.yuukomponent.api.addons.error.TooManyImplementingAddonsFailure;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Shitty singleton
 * @param <T> the addon to load
 */
public class BasicAddonScalar<T extends Addon> implements AddonScalar<T> {

    private final Class<T> clazz;
    private final AtomicBoolean isSet = new AtomicBoolean(false);

    volatile T reference;

    public BasicAddonScalar(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void loadTo(AddonManager manager) throws NoSuchAddonFeatureFailure, TooManyImplementingAddonsFailure {
        if (isSet.compareAndSet(false, true)) {
            this.reference = manager.getAddon(clazz);
        }

        throw new IllegalStateException("Scalar was loaded twice? How did this happen?");
    }

    @Override
    public T retrieve() {
        if (reference == null) throw new IllegalStateException("Scalar was not loaded for object of type: " + clazz);

        return reference;
    }
}
