package com.superyuuki.yuukomponent.dazzleconf.loader.error;

import com.superyuuki.yuukomponent.api.behavior.error.BadConfigFailure;

public class SyntaxFailure extends BadConfigFailure {

    public SyntaxFailure(String conf, String def) {
        super(String.format("The syntax for a key: %s in file: %s is wrong!", def, conf));
    }

    @Override
    public String solution() {
        return "Try to fix the formatting of that component definition or contact config support.";
    }
}
