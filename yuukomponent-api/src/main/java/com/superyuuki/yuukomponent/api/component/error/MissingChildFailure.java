package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

import java.util.UUID;

public class MissingChildFailure extends YuuKomponentFailure {
    public MissingChildFailure(UUID parent, UUID child) {
        super(String.format("Missing a parent-child relationship for parent: %s and child: %s when one was expected!", parent, child));
    }

    @Override
    public String solution() {
        return Const.GENERIC_SOLUTION;
    }
}
