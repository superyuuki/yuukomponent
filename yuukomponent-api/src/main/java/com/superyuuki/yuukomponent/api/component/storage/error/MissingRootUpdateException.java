package com.superyuuki.yuukomponent.api.component.storage.error;

import com.superyuuki.yuukomponent.api.exception.Failures;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

import java.util.UUID;

public class MissingRootUpdateException extends YuuKomponentException {
    public MissingRootUpdateException(UUID toReplace, UUID toInsert) {
        super(String.format("Missing a root component for component: %s when trying to replace %s with component: %s", toReplace, toReplace, toInsert));
    }

    @Override
    public String solution() {
        return Failures.GENERIC_BAD_SCHEMA_CUR;
    }
}
