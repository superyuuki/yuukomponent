package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.component.StructureSearcher;
import com.superyuuki.yuukomponent.api.component.newtype.CachedStructDriver;
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
public class ShittySearcher implements StructureSearcher {

    private final FactoryOfTheFuture factory;
    private final CachedStructDriver structDriver;

    public ShittySearcher(FactoryOfTheFuture factory, CachedStructDriver structDriver) {
        this.factory = factory;
        this.structDriver = structDriver;
    }

    @Override
    public CentralisedFuture<List<UUID>> ordered(UUID uuid) {

        List<UUID> syncAttemptCollection = new ArrayList<>();
        syncAttemptCollection.add(uuid);

        if (searchCached(uuid, syncAttemptCollection)) {
            return factory.completedFuture(syncAttemptCollection);
        }

        //oh no, something is missing! Anyways, run everything async lol. Can still be fast even with the cost
        //of running an async task in that at least some of the values might be cached...
        return factory.supplyAsync(() -> {
            List<UUID> asyncAttemptCollection = new ArrayList<>(); //new thread-enclosed collection for threadsafety

            searchBlocking(uuid, asyncAttemptCollection);

            return asyncAttemptCollection;
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

        if (query.isEmpty()) return false;

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

