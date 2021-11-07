package com.superyuuki.yuukomponent.api.config.error;

import com.superyuuki.yuukomponent.api.YuuKomponentFailure;

public abstract class BadConfigFailure extends YuuKomponentFailure {



    public BadConfigFailure(String message) {
        super(message);
    }

    public BadConfigFailure(Throwable cause) {
        super(cause);
    }



}
