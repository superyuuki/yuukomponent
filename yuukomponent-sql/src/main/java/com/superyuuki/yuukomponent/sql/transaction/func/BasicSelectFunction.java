package com.superyuuki.yuukomponent.sql.transaction.func;

import com.superyuuki.yuukomponent.api.component.Transaction;
import space.arim.jdbcaesar.transact.TransactionQuerySource;
import space.arim.omnibus.util.UUIDUtil;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class BasicSelectFunction implements Function<UUID, List<UUID>> {

    private final Transaction transaction;

    public BasicSelectFunction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public List<UUID> apply(UUID select) {
        return transaction
                .property(TransactionQuerySource.class)
                .query("SELECT child FROM components_relations WHERE parent = ?")
                .params(select)
                .listResult(element -> UUIDUtil.fromByteArray(element.getBytes("child")))
                .execute();
                /*.singleResult(set -> {
                    List<UUID> uuids = new ArrayList<>();

                    while (set.next()) {
                        uuids.add(UUIDUtil.fromByteArray(set.getBytes("child")));
                    }

                    return uuids;
                }).execute();*/
    }
}
