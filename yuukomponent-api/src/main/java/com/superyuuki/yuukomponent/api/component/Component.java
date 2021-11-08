package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.Optional;
import java.util.UUID;

public interface Component {

    void handle(Event event) throws ComponentExecutionFailure;

    boolean present(UUID uuid);
    Optional<Component> search(UUID uuid);

    //Slot operations


}
