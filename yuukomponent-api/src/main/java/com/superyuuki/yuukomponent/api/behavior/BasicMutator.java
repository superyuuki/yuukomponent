package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Basic Mutator. Best for general-purpose threaded (threadsafe) operations that don't require an entirely new task to be spawned
 */
public class BasicMutator implements Mutator {

    private final Behavior behavior;

    public BasicMutator(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event) {
        return top.thenRun(() -> behavior.handle(event));
    }
}
