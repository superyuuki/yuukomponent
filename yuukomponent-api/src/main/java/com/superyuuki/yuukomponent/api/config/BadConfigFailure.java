package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.YuuKomponentFailure;

public abstract class BadConfigFailure extends YuuKomponentFailure {

    public BadConfigFailure(String message) {
        super(message);
    }

}
