package com.superyuuki.yuukomponent.core.centralized.event;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.core.Component;
import com.superyuuki.yuukomponent.core.centralized.ComponentStorage;
import com.superyuuki.yuukomponent.core.centralized.EventDriver;

import java.util.Optional;
import java.util.UUID;

public class EdrBase implements EventDriver {

    private final ComponentStorage storage;

    public EdrBase(ComponentStorage storage) {
        this.storage = storage;
    }

    @Override
    public void dispatch(Event event) {
        for (Component component : storage.components()) {
            component.handle(event);
        }
    }

    @Override
    public boolean dispatch(Event event, UUID uuid) {
        Optional<Component> opt = storage.retrieveOpt(uuid);

        if (opt.isPresent()) {
            opt.get().handle(event);
            return true;
        }

        return false;
    }

}
