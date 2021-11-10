package com.superyuuki.yuukomponent.core.event;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public class NoKeyFailure extends YuuKomponentFailure {
    public NoKeyFailure(String cause) {
        super(String.format("Missing key: %s", cause));
    }

    @Override
    public String solution() {
        return "This should never appear for a user. If this does, someone has fucked up the code AND it has gotten past unit tests.";
    }
}
