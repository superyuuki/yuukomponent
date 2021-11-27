package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public interface StructureDispatcher {

    CentralisedFuture<?> dispatch(UUID uuid, Event event); //dispatch to all children
    CentralisedFuture<?> dispatchImmediate(UUID uuid, Event event); //dispatch to immediate children
    CentralisedFuture<?> dispatchAll(Supplier<Event> event);


}
