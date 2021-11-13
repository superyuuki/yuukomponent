package com.superyuuki.yuukomponent.sql.transaction.func;

import com.github.benmanes.caffeine.cache.Cache;
import com.superyuuki.yuukomponent.api.transactional.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class LoadSelectFunction implements Function<UUID, List<UUID>> {

    private final Transaction transaction;
    private final Cache<UUID, List<UUID>> children;
    private final Cache<UUID, Optional<UUID>> parents;

    public LoadSelectFunction(Transaction transaction, Cache<UUID, List<UUID>> children, Cache<UUID, Optional<UUID>> parents) {
        this.transaction = transaction;
        this.children = children;
        this.parents = parents;
    }

    @Override
    public List<UUID> apply(UUID uuid) {
        return load(uuid, uuid);
    }

    List<UUID> load(UUID topParent, UUID cursorParent) {
        List<UUID> uuids = children.get(cursorParent, new BasicSelectFunction(transaction));

        for (UUID uuid : uuids) {
            parents.put(uuid, Optional.of(topParent));

            load(topParent, uuid);
        }

        return uuids;
    }
}
