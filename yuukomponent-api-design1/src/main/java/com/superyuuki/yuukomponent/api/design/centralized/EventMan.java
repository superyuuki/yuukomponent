package com.superyuuki.yuukomponent.api.design.centralized;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.UUID;

public interface EventMan {

    //sends event to all cumponents
    void event(Event event);

    //sends event to one component
    void event(UUID component, Event event);

}
