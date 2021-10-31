package com.superyuuki.yuukomponent.core.centralized;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.UUID;

public interface IntelligentDriver {

    boolean dispatchChildInclusive(Event event, UUID uuid);

}
