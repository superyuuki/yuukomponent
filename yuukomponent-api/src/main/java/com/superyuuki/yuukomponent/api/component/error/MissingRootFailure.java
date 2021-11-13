package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

import java.util.UUID;

public class MissingRootFailure extends YuuKomponentFailure {
    public MissingRootFailure(UUID uuid) {
        super(String.format("Missing a root-child mapping for child component: %s", uuid));
    }

    @Override
    public String solution() {
        return Const.GENERIC_SOLUTION;
    }
}
