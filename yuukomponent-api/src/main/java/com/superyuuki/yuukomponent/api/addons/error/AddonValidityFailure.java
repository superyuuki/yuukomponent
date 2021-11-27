package com.superyuuki.yuukomponent.api.addons.error;

import com.superyuuki.yuukomponent.api.StartupFailure;

public abstract class AddonValidityFailure extends StartupFailure {

    public AddonValidityFailure(String message) {
        super(message);
    }

    public AddonValidityFailure(Throwable cause) {
        super(cause);
    }
}
