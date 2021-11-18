package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.component.error.MissingRootException;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.newtype.StructDriver;
import space.arim.jdbcaesar.JdbCaesar;
import space.arim.omnibus.util.UUIDUtil;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SQLStructDriver implements StructDriver {

    private final JdbCaesar caesar;

    public SQLStructDriver(JdbCaesar caesar) {
        this.caesar = caesar;
    }

    @Override
    public List<UUID> get(UUID uuid) throws NoSuchComponentException {
        return caesar.query("SELECT child FROM components_relations WHERE parent = ?")
                .params(uuid)
                .listResult(element -> UUIDUtil.fromByteArray(element.getBytes("child")))
                .execute();
    }

    @Override
    public boolean remove(UUID parent, UUID child) throws MissingRootException {
        boolean result = caesar.query("DELETE FROM components_relations WHERE parent = ? AND child = ?")
                .params(parent, child)
                .updateCount(uc -> uc == 1)
                .execute();

        if (result) {
            caesar.query("DELETE FROM components_roots WHERE child = ?")
                    .params(child)
                    .voidResult()
                    .execute(); //we don't care if query successful since it could be a root component with no children
        }

        return result;
    }

    @Override
    public Optional<UUID> add(UUID parent, UUID child) {
        boolean updated = caesar.query("INSERT INTO components_relations (parent, child) VALUES (?, ?)")
                .params(parent, child)
                .updateCount(uc -> uc == 1)
                .execute();

        if (updated) {
            UUID nullable = caesar.query("SELECT root_parent FROM components_roots WHERE child = ?")
                    .params(parent)
                    .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent")))
                    .execute();

            if (nullable == null) {
                nullable = parent; //if the parent is not a child component, make the parent a root component and insert child and parent-as-root to table
                //otherwise, use parent's root as root
                //what a fucking mess
            }

            caesar.query("INSERT INTO components_roots (child, root_parent) VALUES (?, ?)")
                    .params(child, nullable)
                    .voidResult()
                    .execute(); //if already present we don't care

            return Optional.of(nullable);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UUID> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException {
        boolean result = caesar
                .query("UPDATE components_relations SET child = ? WHERE parent = ? AND child = ?")
                .params(childToInsert, parent, childToReplace)
                .updateCount(uc -> uc == 1)
                .execute();

        if (result) {
            UUID nullable = caesar.query("SELECT root_parent FROM components_roots WHERE child = ?")
                    .params(parent)
                    .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent")))
                    .execute();

            if (nullable == null) {
                nullable = parent;
            }

            boolean result2 = caesar
                    .query("UPDATE components_roots SET child = ? WHERE root_parent = ? AND child = ?")
                    .params(childToInsert, nullable, childToReplace)
                    .updateCount(uc -> uc == 1)
                    .execute();

            if (!result2) throw new MissingRootUpdateException(childToReplace, childToInsert);

            return Optional.of(nullable);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UUID> getParent(UUID toCheck) {

        UUID parappa = caesar.query("SELECT root_parent FROM components_roots WHERE child = ?")
                .params(toCheck)
                .singleResult(set -> UUIDUtil.fromByteArray(set.getBytes("root_parent"))).execute();

        return Optional.ofNullable(parappa);
    }

    @Override
    public List<UUID> allWithoutParents(UUID toCheck) {
        throw new UnsupportedOperationException("Implement later since no one gives a shit");
    }
}
