package com.superyuuki.yuukomponent.api.event;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.UUID;

public interface EventDispatcher {

    boolean dispatchChildInclusive(Event event, UUID uuid);

}
