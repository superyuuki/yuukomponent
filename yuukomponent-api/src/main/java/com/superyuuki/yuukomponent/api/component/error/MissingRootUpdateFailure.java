package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

import java.util.UUID;

public class MissingRootUpdateFailure extends YuuKomponentFailure {
    public MissingRootUpdateFailure(UUID toReplace, UUID toInsert) {
        super(String.format("Missing a root component for component: %s when trying to replace %s with component: %s", toReplace, toReplace, toInsert));
    }

    @Override
    public String solution() {
        return Const.GENERIC_SOLUTION;
    }
}
