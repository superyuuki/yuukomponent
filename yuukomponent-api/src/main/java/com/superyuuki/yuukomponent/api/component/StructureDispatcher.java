package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public interface StructureDispatcher {

    CompletableFuture<?> dispatch(UUID uuid, Event event); //dispatch to all children
    CompletableFuture<?> dispatchImmediate(UUID uuid, Event event); //dispatch to immediate children
    CompletableFuture<?> dispatchAll(Supplier<Event> event);


}
