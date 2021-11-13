package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Sync mutator. Best used for actions that need to be ran explicitly on a synchronous platform like bukkit
 */
public class SyncMutator implements Mutator {

    private final Behavior behavior;

    public SyncMutator(Behavior behavior) {
        this.behavior = behavior;
    }

    @Override
    public CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event) {
        return top.thenRunSync(() -> behavior.handle(event));
    }
}
