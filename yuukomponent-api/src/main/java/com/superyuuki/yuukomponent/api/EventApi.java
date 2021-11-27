package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.behavior.Event;

public interface EventApi {

    /**
     * Submits an event to all components
     * @param event the event ID
     */
    void submit(Event event);
}
