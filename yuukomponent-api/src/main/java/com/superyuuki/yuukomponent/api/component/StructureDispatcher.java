package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

/**
 * Sequential structure dispatcher. Atomic synchronous modifications to passed events are permitted.
 */
public interface StructureDispatcher {

    <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, Supplier<T> event);
    <T extends Event> CentralisedFuture<?> dispatchRecursive(UUID uuid, Supplier<T> event);
    <T extends Event> CentralisedFuture<?> dispatchAll(Supplier<T> event);

}
