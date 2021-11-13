package com.superyuuki.yuukomponent.api.component.structure;

import com.superyuuki.yuukomponent.api.behavior.Event;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.ReactionStage;

import java.util.UUID;

public interface Structure {

    <T extends Event> CentralisedFuture<?> handle(T event);

    Structure without(UUID component);

    boolean is(UUID component);
    boolean contains(UUID component);

}
