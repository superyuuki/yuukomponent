package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.ConstFutures;
import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.ComponentDispatcher;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.UUID;

public class BasicComponentDispatch implements ComponentDispatcher {

    private final FactoryOfTheFuture factory;

    public BasicComponentDispatch(FactoryOfTheFuture factory) {
        this.factory = factory;
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, T event) {
        return dispatch(uuid, event, factory.completedFuture(ConstFutures.COMPLETED));
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, T event, CentralisedFuture<?> top) {
        return null;
    }
}
