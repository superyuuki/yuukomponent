package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Bind to behavior
 */
public interface Mutator {

    CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event);

    static Mutator sync(Behavior behavior) {
        return new SyncMutator(behavior);
    }

    static Mutator async(Behavior behavior) {
        return new SyncMutator(behavior);
    }

}
