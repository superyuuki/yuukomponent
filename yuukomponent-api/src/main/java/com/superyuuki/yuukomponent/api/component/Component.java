package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Workspace for behaviors
 */
public interface Component {

    <T extends Event> CentralisedFuture<?> compute(T event);

}
