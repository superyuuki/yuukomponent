package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentPoolFeature;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.config.ComponentRegistry;
import com.superyuuki.yuukomponent.api.config.error.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.transactional.Transaction;
import space.arim.jdbcaesar.transact.TransactionQuerySource;

import java.util.UUID;

public class SQLComponentPoolFeature implements ComponentPoolFeature {

    private final ComponentRegistry registry;

    public SQLComponentPoolFeature(ComponentRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Component get(Transaction tx, UUID uuid) throws NoSuchDefinitionException, NoSuchComponentException {

        String nullable = tx.property(TransactionQuerySource.class)
                .query("SELECT component_def FROM components WHERE uuid = ?")
                .params(uuid)
                .singleResult(set -> set.getString("component_def"))
                .execute();

        if (nullable == null) throw new NoSuchComponentException(uuid);

        return registry.createOfType(nullable);
    }

    @Override
    public void delete(Transaction tx, UUID uuid) throws NoSuchComponentException {
        boolean isSucc = tx.property(TransactionQuerySource.class)
                .query("DELETE FROM components WHERE uuid = ?")
                .params(uuid)
                .updateCount(uc -> uc == 1)
                .execute();

        if (!isSucc) throw new NoSuchComponentException(uuid);
    }
}
