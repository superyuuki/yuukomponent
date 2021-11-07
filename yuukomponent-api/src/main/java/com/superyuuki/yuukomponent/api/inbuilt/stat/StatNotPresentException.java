package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.YuuKomponentException;

public class StatNotPresentException extends YuuKomponentException {

    public StatNotPresentException(String message) {
        super(message);
    }

}
