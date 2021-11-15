package com.superyuuki.yuukomponent.core.component.dispatcher;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.StructureDispatcher;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public abstract class AbstractStructDispatch implements StructureDispatcher {

    protected final MonoStructurePool pool;
    protected final FactoryOfTheFuture factory;

    protected AbstractStructDispatch(MonoStructurePool pool, FactoryOfTheFuture factory) {
        this.pool = pool;
        this.factory = factory;
    }


    @Override
    public <T extends Event> CentralisedFuture<?> dispatchRecursive(UUID uuid, Supplier<T> event) {
        return pool.getParent(uuid)
                .thenApply(opt -> {
                    if (opt.isEmpty()) throw new MissingParentException(uuid);

                    return opt.get();
                }).thenCompose(target -> dispatch(target, event));
    }

    @Override
    public <T extends Event> CentralisedFuture<?> dispatchAll(Supplier<T> event) {
        return pool.withoutParents().thenCompose(withoutParents -> {
            List<CentralisedFuture<?>> whoCares = new ArrayList<>();

            for (UUID uuid : withoutParents) {
                whoCares.add(dispatch(uuid, event));
            }

            return factory.allOf(whoCares.toArray(new CentralisedFuture[]{}));
        });
    }
}
