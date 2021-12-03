package com.superyuuki.yuukomponent.core.stats;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

public class StatNotPresentException extends YuuKomponentException {

    public StatNotPresentException(String message) {
        super(message);
    }

}
