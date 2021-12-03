package com.superyuuki.yuukomponent.bootstrap.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.StructureDispatcher;
import com.superyuuki.yuukomponent.api.component.StructureQuerier;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class SimpleStructureDispatcher implements StructureDispatcher {

    private final FactoryOfTheFuture factory;
    private final StructureQuerier structureQuerier;
    private final BehaviorQuerier behaviorQuerier;
    private final ParentQuerier parentQuerier;

    public SimpleStructureDispatcher(FactoryOfTheFuture factory, StructureQuerier structureQuerier, BehaviorQuerier behaviorQuerier, ParentQuerier parentQuerier) {
        this.factory = factory;
        this.structureQuerier = structureQuerier;
        this.behaviorQuerier = behaviorQuerier;
        this.parentQuerier = parentQuerier;
    }

    @Override
    public CentralisedFuture<?> dispatch(UUID uuid, Event event) {
        return structureQuerier.ordered(uuid).thenCompose(behaviorQuerier::ordered).thenAcceptSync(behaviors -> {
            for (Behavior behavior : behaviors) {
                behavior.handle(event);
            }
        });
    }

    @Override
    public CentralisedFuture<?> dispatchImmediate(UUID uuid, Event event) {
        return structureQuerier.orderedImmediate(uuid).thenCompose(behaviorQuerier::ordered).thenAcceptSync(behaviors -> {
            for (Behavior behavior : behaviors) {
                behavior.handle(event);
            }
        });
    }

    @Override
    public CentralisedFuture<?> dispatchAll(Supplier<Event> event) {
        return parentQuerier.withoutParents().thenCompose(starts -> {

            List<CentralisedFuture<?>> futures = new ArrayList<>();

            for (UUID uuid : starts) {
                futures.add(dispatch(uuid, event.get()));
            }

            return factory.allOf(futures.toArray(new CentralisedFuture[]{}));
        });
    }
}
