package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonContext;
import com.superyuuki.yuukomponent.api.addons.error.AddonValidityFailure;

import java.util.Optional;

public interface Validator {

    AddonContext validate(Class<?> clazz) throws AddonValidityFailure;
    Optional<Class<? extends Addon>> translate(Class<?> clazz);


}
