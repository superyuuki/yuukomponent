package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public class HasParametersFailure extends AddonValidityFailure {
    public HasParametersFailure(Class<?> addon) {
        super(String.format("The addon: %s has parameters in its' constructors", addon.getCanonicalName()));
    }

    @Override
    public String solution() {
        return "Parameterized constructors are not allowed for YuuKomponent addons. Please remove the constructor and try again.";
    }
}
