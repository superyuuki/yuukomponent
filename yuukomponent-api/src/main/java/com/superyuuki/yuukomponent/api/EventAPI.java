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

    /**
     * Submits a targeted event to a specific component. Only selects active components.
     * @param identifier the identifier of the component
     * @param event the event to submit
     * @return true if the component was found and the event could be submitted, false if not
     */
    boolean submitTo(UUID identifier, Event event);

    /**
     * Submits a targeted event to a specific component. First checks active components and if not checks their children.
     * @param identifier the identifier of the component
     * @param event the event to submit
     * @return true if the component was found and the event could be submitted, false if not
     */
    boolean submitChildren(UUID identifier, Event event);
}
