package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

import java.util.UUID;

public interface ComponentPool {

    Component get(Transaction tx, UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException;
    void delete(Transaction tx, UUID uuid) throws NoSuchComponentException;

}
