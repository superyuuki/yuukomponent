package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.YuuKomponentException;
import com.superyuuki.yuukomponent.api.YuuKomponentFailure;

public class NoSuchBehaviorException extends YuuKomponentFailure {

    public NoSuchBehaviorException(String message) {
        super("No such behavior exists with id: " + message);
    }
}
