package com.superyuuki.yuukomponent.api.trait;

import com.superyuuki.yuukonstants.Failure;

public class TraitNotDefinedFailure extends Failure {

    public TraitNotDefinedFailure(String type) {
        super("No such trait exists with the type: " + type);
    }
}
