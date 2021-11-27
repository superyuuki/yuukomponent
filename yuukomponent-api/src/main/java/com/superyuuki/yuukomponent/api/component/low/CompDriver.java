package com.superyuuki.yuukomponent.api.component.low;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.annotate.Heavy;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

import java.util.UUID;

public interface CompDriver {

    @Heavy
    Behavior get(UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException;

    @Heavy
    boolean present(UUID uuid);

    @Heavy
    boolean delete(UUID uuid);

}
