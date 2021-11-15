package com.superyuuki.yuukomponent.core.component.dispatcher;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.ComponentDispatcher;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@Deprecated //Does not preserve event modification order and therefore does not meet yuukomponent schema specification
public class BasicStructDispatch extends AbstractStructDispatch {

    private final ComponentDispatcher dispatcher;

    public BasicStructDispatch(MonoStructurePool pool, FactoryOfTheFuture factory, ComponentDispatcher dispatcher) {
        super(pool, factory);
        this.dispatcher = dispatcher;
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, Supplier<T> event) {
        return combine(uuid, event.get());
    }

    <T extends Event> CentralisedFuture<?> combine(UUID uuid, T event) {
        CentralisedFuture<?> top = dispatcher.dispatch(uuid, event);

        CentralisedFuture<UUID[]> collection = pool.get(uuid);

        return top.thenCombine(collection, (a, col) -> {
            List<CentralisedFuture<?>> futures = new ArrayList<>();

            for (UUID uuid1 : col) {
                futures.add(combine(uuid1, event));
            }

            return CentralisedFuture.allOf(futures.toArray(new CompletableFuture[]{}));
        });
    }

}
