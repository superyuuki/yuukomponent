package com.superyuuki.yuukomponent.api.config.behavior.error;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

public class WrongTypeValueFailure extends BadConfigFailure {


    public WrongTypeValueFailure(String definition, String valId, String expectedClass, String badClass) {
        super(String.format("A component with definition: %s was expecting a value with id: %s with type %s but was given a value with type %s",
                definition, valId, expectedClass, badClass));
    }

    @Override
    public String solution() {
        return "Change the type of the value to fit the specifications shown. If you cannot figure it out, check the wiki or contact support.";
    }
}
