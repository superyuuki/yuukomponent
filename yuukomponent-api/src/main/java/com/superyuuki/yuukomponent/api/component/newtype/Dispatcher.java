package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.concurrent.CompletableFuture;

public interface Dispatcher {

    CompletableFuture<?> dispatch(int uuid, Event event);

    /**
     * Tries to find the parent of this component and dispatch to it.
     * @param uuid
     * @param event
     */
    CompletableFuture<?> dispatchRoot(int uuid, Event event);

    CompletableFuture<?> dispatchAll(Event event);

}
