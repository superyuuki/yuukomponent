package com.superyuuki.yuukomponent.dazzleconf.loader.error;

import com.superyuuki.yuukomponent.api.trait.error.BadConfigFailure;

public class UnspecifiedConfigFailure extends BadConfigFailure {

    public UnspecifiedConfigFailure(Throwable cause) {
        super(cause);
    }

    @Override
    public String solution() {
        return "Something has gone wrong that the developers did not forsee. Please copy the contents of this failure and send them to support.";
    }
}
