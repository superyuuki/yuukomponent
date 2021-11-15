package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

public class NoSuchTxPropertyException extends YuuKomponentException {
    public NoSuchTxPropertyException(String message) {
        super(String.format("No such property exists on transaction for property: %s", message));
    }

    @Override
    public String solution() {
        //make this better
        return "Contact the YuuKomponent support team and send them this error alongside your details!";
    }
}
