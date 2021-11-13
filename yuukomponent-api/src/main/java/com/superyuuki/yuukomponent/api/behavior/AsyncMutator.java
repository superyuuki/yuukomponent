package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Async mutator. Best used for behaviors that will be needing to perform heavy blocking operations.
 * Doesn't block threads, but still causes all other events to be fired after blocking operations due to the nature
 * of how event firing is set up (concurrent but not parallel). If you don't understand this (i don't) just don't use it lol
 * you probably don't need it
 */
public class AsyncMutator implements Mutator {

    private final Behavior behavior;

    public AsyncMutator(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event) {
        return top.thenRunAsync(() -> behavior.handle(event));
    }

}
