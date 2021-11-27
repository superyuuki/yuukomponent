package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonContext;
import com.superyuuki.yuukomponent.api.addons.error.NotAddonAnnotationFailure;
import com.superyuuki.yuukomponent.api.addons.error.NotAddonInterfaceFailure;

import java.util.Optional;

public class BasicValidator implements Validator {
    @Override
    public AddonContext validate(Class<?> clazz) throws NotAddonAnnotationFailure, NotAddonInterfaceFailure {

        final AddonContext annotation = clazz.getAnnotation(AddonContext.class);

        if (annotation == null) throw new NotAddonAnnotationFailure(clazz.getCanonicalName());
        if (clazz.isAssignableFrom(Addon.class)) throw new NotAddonInterfaceFailure(clazz.getCanonicalName());

        return annotation;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Class<? extends Addon>> translate(Class<?> clazz) {
        if (Addon.class.isAssignableFrom(clazz)) {
            return Optional.of((Class<? extends Addon>) clazz);
        }

        return Optional.empty();
    }
}
