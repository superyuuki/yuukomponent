package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.Event;

/**
 * Marker interface for loaded behavior
 */
public interface Component extends Behavior {

    void handle(Event event);

}
