package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;

/**
 * Dispatcher attached to the component pool
 */
public interface ComponentDispatcher {

    <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, T event); //execute immediately
    <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, T event, CentralisedFuture<?> top); //execute after top

}
