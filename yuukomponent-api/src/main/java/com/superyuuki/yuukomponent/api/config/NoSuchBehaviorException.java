package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class NoSuchBehaviorException extends YuuKomponentException {

    public NoSuchBehaviorException(String message) {
        super("No such behavior exists with id: " + message);
    }
}
