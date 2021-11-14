package com.superyuuki.yuukomponent.api.addons.internal;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonContext;
import com.superyuuki.yuukomponent.api.addons.error.NotAddonAnnotationFailure;
import com.superyuuki.yuukomponent.api.addons.error.NotAddonInterfaceFailure;

public class BasicValidator implements Validator {
    @Override
    public AddonContext validate(Class<?> clazz) throws NotAddonAnnotationFailure, NotAddonInterfaceFailure {

        final AddonContext annotation = clazz.getAnnotation(AddonContext.class);

        if (annotation == null) throw new NotAddonAnnotationFailure(clazz.getCanonicalName());
        if (clazz.isAssignableFrom(Addon.class)) throw new NotAddonInterfaceFailure(clazz.getCanonicalName());

        return annotation;
    }
}
