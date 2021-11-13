package com.superyuuki.yuukomponent.api.behavior;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Bind to behavior
 */
public interface Mutator {

    CentralisedFuture<?> insertBehavior(CentralisedFuture<?> top, Object event);

}
