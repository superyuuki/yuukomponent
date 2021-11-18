package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.StructureDispatcher;
import com.superyuuki.yuukomponent.api.component.newtype.CachedCompDriver;
import com.superyuuki.yuukomponent.api.component.newtype.CachedStructDriver;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class DriverDispatcher implements StructureDispatcher {

    private final FactoryOfTheFuture factory;
    private final CachedCompDriver compDriver;
    private final CachedStructDriver structDriver;

    public DriverDispatcher(FactoryOfTheFuture factory, CachedCompDriver compDriver, CachedStructDriver structDriver) {
        this.factory = factory;
        this.compDriver = compDriver;
        this.structDriver = structDriver;
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, Supplier<T> event) {




        return null;
    }

    //appends values to a list recursively using cached values only until it finds a component id that is not cached
    //or completion.
    //if an uncached component is found component loading is forked to a new async method which everything else carries out on.
    <T extends Event> CentralisedFuture<List<UUID>> uuids(UUID uuid) {
        var opt = structDriver.getCached(uuid);

        if (opt.isPresent()) { //if it exists

            CentralisedFuture<List<UUID>> first = factory.completedFuture(opt.get());

            first.thenCompose(li -> {

                List<UUID> initial = new ArrayList<>(li);

                for (UUID uuid1 : li) {

                }

            })

        } else {
            //if async is needed, move to an async branch
            factory.supplyAsync(() -> {

                List<CentralisedFuture<List<UUID>>> toReturn = new ArrayList<>();

                for (UUID uuid1 : structDriver.get(uuid)) {
                    toReturn.add(uuids(uuid1));
                }

                return toReturn;
            });
        }
    }

    <T extends Event> CentralisedFuture<List<UUID>> uuidsAsync(UUID uuid) {
        List<UUID> uuids = new ArrayList<>();

        for (UUID uuid1 : structDriver.get(uuid)) {

        }
    }



    @Override
    public <T extends Event> CentralisedFuture<?> dispatchImmediate(UUID uuid, Supplier<T> event) {
        return null;
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatchAll(Supplier<T> event) {
        return null;
    }
}
