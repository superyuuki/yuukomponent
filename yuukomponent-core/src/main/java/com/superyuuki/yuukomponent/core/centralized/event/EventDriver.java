package com.superyuuki.yuukomponent.core.centralized.event;

import com.superyuuki.yuukomponent.api.Event;

import java.util.UUID;

public interface EventDriver {

    void dispatch(Event event);
    boolean dispatch(Event event, UUID uuid);


}
