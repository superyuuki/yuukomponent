package com.superyuuki.yuukomponent.dazzleconf.loader.error;

import com.superyuuki.yuukomponent.api.behavior.error.BadConfigFailure;

public class FormatFailure extends BadConfigFailure {

    public FormatFailure(String conf) {
        super(String.format("The configuration file %s has a format error", conf));
    }

    @Override
    public String solution() {
        return "Format errors indicate that the entire file's configuration structure is broken. Try to put your configuration file into a YML parser online (google it) and check closely to what line it points to. Contact support after that occurs.";
    }
}
