package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


public interface StructureDispatcher {

    <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, Supplier<T> event); //dispatch to all children
    <T extends Event> CentralisedFuture<?> dispatchImmediate(UUID uuid, Supplier<T> event); //dispatch to immediate children


}
