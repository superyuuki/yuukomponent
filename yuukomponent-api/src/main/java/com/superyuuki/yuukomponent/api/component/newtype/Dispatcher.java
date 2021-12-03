package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;

public interface Dispatcher {

    CentralisedFuture<?> dispatch(int uuid, Event event);

    /**
     * Tries to find the parent of this component and dispatch to it.
     * @param uuid
     * @param event
     */
    CentralisedFuture<?> dispatchRoot(int uuid, Event event);

    CentralisedFuture<?> dispatchAll(Event event);

}
