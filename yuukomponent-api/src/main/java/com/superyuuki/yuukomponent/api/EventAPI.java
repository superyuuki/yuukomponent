package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.Component;

import java.util.UUID;

public interface EventAPI {

    /**
     * Submits an event to all components
     * @param event the event ID
     */
    void submit(Event event);
}
