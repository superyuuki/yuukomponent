package com.superyuuki.yuukomponent.sql.transaction.func;

import com.superyuuki.yuukomponent.api.transactional.Transaction;
import space.arim.jdbcaesar.transact.TransactionQuerySource;
import space.arim.omnibus.util.UUIDUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class ParentSelectFunction implements Function<UUID, Optional<UUID>> {

    private final Transaction transaction;

    public ParentSelectFunction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Optional<UUID> apply(UUID uuid) {
        return Optional.ofNullable(transaction
                .property(TransactionQuerySource.class)
                .query("SELECT root_parent FROM components_roots WHERE child = ?")
                .params(uuid)
                .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent"))).execute());
    }
}
