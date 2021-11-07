package com.superyuuki.yuukomponent.api.config.error;

public class UnspecifiedConfigFailure extends BadConfigFailure {

    public UnspecifiedConfigFailure(String message) {
        super(message);
    }

    @Override
    public String solution() {
        return "Something has gone wrong that the developers did not forsee. Please copy the contents of this failure and send them to support.";
    }
}
