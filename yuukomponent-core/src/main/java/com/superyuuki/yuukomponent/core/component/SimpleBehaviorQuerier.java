package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.BehaviorQuerier;
import com.superyuuki.yuukomponent.api.component.low.CompPool;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SimpleBehaviorQuerier implements BehaviorQuerier {

    private final CompPool compPool;
    private final FactoryOfTheFuture factory;

    public SimpleBehaviorQuerier(CompPool compPool, FactoryOfTheFuture factory) {
        this.compPool = compPool;
        this.factory = factory;
    }

    @Override
    public CentralisedFuture<List<Behavior>> ordered(List<UUID> uuids) {

        List<Behavior> syncAttemptList = new ArrayList<>();

        if (populateCached(uuids, syncAttemptList)) {
            return factory.completedFuture(List.copyOf(syncAttemptList));
        }

        return factory.supplyAsync(() -> {
            List<Behavior> asyncAttemptList = new ArrayList<>();

            populateBlocking(uuids, asyncAttemptList);

            return List.copyOf(asyncAttemptList);
        });

    }

    boolean populateCached(List<UUID> uuids, List<Behavior> toAddTo) {
        for (UUID uuid : uuids) {
            Optional<Behavior> opt = compPool.getCached(uuid);

            if (opt.isEmpty()) return false;

            toAddTo.add(opt.get());
        }

        return true;
    }

    void populateBlocking(List<UUID> uuids, List<Behavior> toAddTo) {
        for (UUID uuid : uuids) {
            Behavior behavior = compPool.get(uuid);

            toAddTo.add(behavior);
        }
    }
}
