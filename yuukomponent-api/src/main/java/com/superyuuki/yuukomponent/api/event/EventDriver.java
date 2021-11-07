package com.superyuuki.yuukomponent.api.event;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.UUID;

public interface EventDriver {

    void dispatch(Event event);
    boolean dispatch(Event event, UUID uuid);


}
