package com.superyuuki.yuukomponent.core.centralized.event;

import com.superyuuki.yuukomponent.api.Event;

import java.util.UUID;

public interface EventDispatcher {

    boolean dispatchChildInclusive(Event event, UUID uuid);

}
