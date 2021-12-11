package com.superyuuki.yuukomponent.api.component.storage.error;

import com.superyuuki.yuukomponent.api.exception.Failures;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

import java.util.UUID;

public class MissingRootException extends YuuKomponentException {
    public MissingRootException(UUID uuid) {
        super(String.format("Missing a root-child mapping for child component: %s", uuid));
    }

    @Override
    public String solution() {
        return Failures.GENERIC_BAD_SCHEMA_CUR;
    }
}
