package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Mutator that 
 */
public class DetachedMutator implements Mutator {

    private final Behavior behavior;

    public DetachedMutator(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event) {
        top.thenRunSync(() -> behavior.handle(event)); //violates a bunch of shit

        return top;
    }
}
