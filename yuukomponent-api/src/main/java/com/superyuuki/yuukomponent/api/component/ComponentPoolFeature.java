package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.config.error.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.transactional.Transaction;

import java.util.UUID;

public interface ComponentPoolFeature {

    Component get(Transaction tx, UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException;
    void delete(Transaction tx, UUID uuid) throws NoSuchComponentException;

}
