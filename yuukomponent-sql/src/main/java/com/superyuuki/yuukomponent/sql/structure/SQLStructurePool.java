package com.superyuuki.yuukomponent.sql.structure;

import com.superyuuki.yuukomponent.api.component.error.MissingRootFailure;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateFailure;
import com.superyuuki.yuukomponent.api.component.StructurePool;
import com.superyuuki.yuukomponent.api.component.Transaction;
import space.arim.jdbcaesar.transact.TransactionQuerySource;
import space.arim.omnibus.util.UUIDUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SQLStructurePool implements StructurePool {
    @Override
    public List<UUID> get(Transaction tx, UUID uuid) {
        return tx
                .property(TransactionQuerySource.class)
                .query("SELECT child FROM components_relations WHERE parent = ?")
                .params(uuid)
                .listResult(element -> UUIDUtil.fromByteArray(element.getBytes("child")))
                .execute();
    }

    @Override
    public boolean remove(Transaction tx, UUID parent, UUID child) throws MissingRootFailure {

        TransactionQuerySource source = tx.property(TransactionQuerySource.class);

        boolean result = source
                .query("DELETE FROM components_relations WHERE parent = ? AND child = ?")
                .params(parent, child)
                .updateCount(uc -> uc == 1)
                .execute();

        if (result) {
            source
                    .query("DELETE FROM components_roots WHERE child = ?")
                    .params(child)
                    .voidResult()
                    .execute(); //we don't care if query successful since it could be a root component with no children
        }

        return result;
    }

    @Override
    public Optional<UUID> add(Transaction tx, UUID parent, UUID child) {
        TransactionQuerySource source = tx.property(TransactionQuerySource.class);

        boolean updated = source.query("INSERT INTO components_relations (parent, child) VALUES (?, ?)")
                .params(parent, child)
                .updateCount(uc -> uc == 1)
                .execute();

        if (updated) {
            UUID nullable = source.query("SELECT root_parent FROM components_roots WHERE child = ?")
                    .params(parent)
                    .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent")))
                    .execute();

            if (nullable == null) {
                nullable = parent; //if the parent is not a child component, make the parent a root component and insert child and parent-as-root to table
                //otherwise, use parent's root as root
                //what a fucking mess
            }

            source.query("INSERT INTO components_roots (child, root_parent) VALUES (?, ?)")
                    .params(child, nullable)
                    .voidResult()
                    .execute(); //if already present we don't care

            return Optional.of(nullable);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UUID> replace(Transaction tx, UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateFailure {
        TransactionQuerySource source = tx.property(TransactionQuerySource.class);

        boolean result = source
                .query("UPDATE components_relations SET child = ? WHERE parent = ? AND child = ?")
                .params(childToInsert, parent, childToReplace)
                .updateCount(uc -> uc == 1)
                .execute();

        if (result) {
            UUID nullable = source.query("SELECT root_parent FROM components_roots WHERE child = ?")
                    .params(parent)
                    .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent")))
                    .execute();

            if (nullable == null) {
                nullable = parent;
            }

            boolean result2 = source
                    .query("UPDATE components_roots SET child = ? WHERE root_parent = ? AND child = ?")
                    .params(childToInsert, nullable, childToReplace)
                    .updateCount(uc -> uc == 1)
                    .execute();

            if (!result2) throw new MissingRootUpdateFailure(childToReplace, childToInsert);

            return Optional.of(nullable);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UUID> getParent(Transaction tx, UUID toCheck) {
        return Optional.ofNullable(tx
                .property(TransactionQuerySource.class)
                .query("SELECT root_parent FROM components_roots WHERE child = ?")
                .params(toCheck)
                .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent"))).execute());
    }

    @Override
    public UUID[] allWithoutParents(Transaction tx, UUID toCheck) {
        throw new UnsupportedOperationException("IMPLEMENT"); //TODO Implement without parents query
    }
}
