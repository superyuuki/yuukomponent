package com.superyuuki.yuukomponent.api.design;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.Optional;
import java.util.UUID;

public interface Component {

    void handle(Event event);

    boolean present(UUID uuid);
    Optional<Component> search(UUID uuid);

}
