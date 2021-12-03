package com.superyuuki.yuukomponent.bootstrap.component;

import com.superyuuki.yuukomponent.api.component.StructureQuerier;
import com.superyuuki.yuukomponent.api.component.low.StructPool;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Shitty stupid searcher implementation that forgoes AsyncLoadingCaches for a simple
 *
 * - if it works with all cached values, we good
 * - otherwise, fork to an async task and continue loading mixed cached/remote
 *
 * Probably more performant than an asyncloadingcache impl due to the fact that it only needs to run a single async task LOL
 */
public class SimpleStructureQuerier implements StructureQuerier {

    private final FactoryOfTheFuture factory;
    private final StructPool structDriver;

    public SimpleStructureQuerier(FactoryOfTheFuture factory, StructPool structDriver) {
        this.factory = factory;
        this.structDriver = structDriver;
    }

    @Override
    public CentralisedFuture<List<UUID>> ordered(UUID uuid) {

        List<UUID> syncAttemptCollection = new ArrayList<>();
        syncAttemptCollection.add(uuid);

        if (searchCached(uuid, syncAttemptCollection)) {
            return factory.completedFuture(List.copyOf(syncAttemptCollection));
        }

        //oh no, something is missing! Anyways, run everything async lol. Can still be fast even with the cost
        //of running an async task in that at least some of the values might be cached...
        return factory.supplyAsync(() -> {
            List<UUID> asyncAttemptCollection = new ArrayList<>(); //new thread-enclosed collection for threadsafety
            asyncAttemptCollection.add(uuid);

            searchBlocking(uuid, asyncAttemptCollection);

            return List.copyOf(asyncAttemptCollection);
        });

    }

    @Override
    public CentralisedFuture<List<UUID>> orderedImmediate(UUID uuid) {
        Optional<List<UUID>> query = structDriver.getCached(uuid);

        if (query.isPresent()) {
            return factory.completedFuture(query.get());
        }

        return factory.supplyAsync(() -> structDriver.get(uuid)); //async tasks are for losers most people don't even get how costly shitmultithreading is.
    }

    boolean searchCached(UUID uuid, List<UUID> collection) {

        Optional<List<UUID>> query = structDriver.getCached(uuid);

        if (query.isEmpty()) return false; //problematic: What if the uuid simply doesn't exist?

        List<UUID> result = query.get();
        collection.addAll(result);
        for (UUID uuid1 : result) {
            if (!searchCached(uuid1, collection)) return false;
        }

        return true;
    }

    void searchBlocking(UUID uuid, List<UUID> collection) {
        List<UUID> result = structDriver.get(uuid);

        collection.addAll(result);
        for (UUID uuid1 : result) {
            searchBlocking(uuid1, collection);
        }
    }
}

