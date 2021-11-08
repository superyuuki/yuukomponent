package com.superyuuki.yuukomponent.stat;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

public class StatNotPresentException extends YuuKomponentException {

    public StatNotPresentException(String message) {
        super(message);
    }

}
