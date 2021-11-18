package com.superyuuki.yuukomponent.sql.transaction;

import com.superyuuki.yuukomponent.api.component.error.NoSuchTxPropertyException;
import space.arim.jdbcaesar.transact.TransactionQuerySource;

public class SQLTransaction implements Transaction {

    private final TransactionQuerySource source;

    public SQLTransaction(TransactionQuerySource source) {
        this.source = source;
    }

    @Override
    public <T> T property(Class<T> property) throws NoSuchTxPropertyException {

        if (property.equals(TransactionQuerySource.class)) {
            return (T) source;
        }

        throw new NoSuchTxPropertyException(property.getCanonicalName());
    }
}
