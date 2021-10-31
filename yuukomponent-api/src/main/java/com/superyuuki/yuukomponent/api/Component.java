package com.superyuuki.yuukomponent.api;

import java.util.Optional;
import java.util.UUID;

public interface Component {

    void handle(Event event);

    boolean present(UUID uuid);
    Optional<Component> search(UUID uuid);

}
