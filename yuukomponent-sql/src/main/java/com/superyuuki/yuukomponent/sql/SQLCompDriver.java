package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.low.CompDriver;
import space.arim.jdbcaesar.JdbCaesar;

import java.util.UUID;

public class SQLCompDriver implements CompDriver {

    private final BehaviorRegistry registry;
    private final JdbCaesar caesar;

    public SQLCompDriver(BehaviorRegistry registry, JdbCaesar caesar) {
        this.registry = registry;
        this.caesar = caesar;
    }

    @Override
    public Behavior get(UUID uuid) throws NoSuchComponentException, NoSuchDefinitionException {
        String def = caesar.query("SELECT component_def FROM components WHERE uuid = ?")
                .params(uuid)
                .singleResult(set -> set.getString("component_def"))
                .execute();

        if (def == null) throw new NoSuchComponentException(uuid);

        return registry.createOfType(def);
    }

    @Override
    public boolean present(UUID uuid) {
        String def = caesar.query("SELECT component_def FROM components WHERE uuid = ?")
                .params(uuid)
                .singleResult(set -> set.getString("component_def"))
                .execute();

        return def == null;
    }

    @Override
    public boolean delete(UUID uuid) throws NoSuchComponentException {
        return caesar.query("DELETE FROM components WHERE uuid = ?")
                .params(uuid)
                .updateCount(uc -> uc == 1)
                .execute();
    }
}
