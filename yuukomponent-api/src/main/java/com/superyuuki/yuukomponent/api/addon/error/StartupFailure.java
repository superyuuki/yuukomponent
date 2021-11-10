package com.superyuuki.yuukomponent.api.addon.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public abstract class StartupFailure extends YuuKomponentFailure {

    public StartupFailure(String message) {
        super(message);
    }

}
