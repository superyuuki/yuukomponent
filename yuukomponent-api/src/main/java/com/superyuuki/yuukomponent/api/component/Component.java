package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

/**
 * Workspace for behaviors
 *
 * May be deleted in a future PR as it implements the same behavior as a.. uh.. Behavior.
 */
public interface Component {

    <T extends Event> CentralisedFuture<?> compute(T event, CentralisedFuture<?> top);

}
