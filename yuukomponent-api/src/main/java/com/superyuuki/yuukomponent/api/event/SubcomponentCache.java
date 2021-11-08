package com.superyuuki.yuukomponent.api.event;

import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentExecutionFailure;

import java.util.Optional;
import java.util.UUID;

public interface SubcomponentCache {

    Optional<Component> get(UUID uuid) throws ComponentExecutionFailure;

    //TODO Implement component-slot migration methods for subcomponent cache

}
