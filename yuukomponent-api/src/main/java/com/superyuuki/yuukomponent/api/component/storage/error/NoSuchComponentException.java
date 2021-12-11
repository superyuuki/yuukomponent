package com.superyuuki.yuukomponent.api.component.storage.error;

import com.superyuuki.yuukomponent.api.exception.Failures;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

public class NoSuchComponentException extends YuuKomponentException
{
    public NoSuchComponentException(int cause) {
        super(String.format("A component with id: %s was queried for but no such component exists!", cause));
    }

    @Override
    public String solution() {
        return Failures.GENERIC_NO_COMPONENT;
    }
}
