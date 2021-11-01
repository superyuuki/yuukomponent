package com.superyuuki.yuukomponent.core.event;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.UUID;

public interface EventDriver {

    void dispatch(Event event);
    boolean dispatch(Event event, UUID uuid);


}
