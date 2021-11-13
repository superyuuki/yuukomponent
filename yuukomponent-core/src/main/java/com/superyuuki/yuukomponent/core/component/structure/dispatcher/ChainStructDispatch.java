package com.superyuuki.yuukomponent.core.component.structure.dispatcher;

import com.superyuuki.yuukomponent.api.Futures;
import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.ComponentDispatcher;
import com.superyuuki.yuukomponent.api.component.structure.MonoStructurePool;
import com.superyuuki.yuukomponent.core.component.structure.util.ArrayFunc;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.UUID;
import java.util.function.Supplier;

public class ChainStructDispatch extends AbstractStructDispatch {

    private final ComponentDispatcher dispatcher;

    public ChainStructDispatch(MonoStructurePool pool, FactoryOfTheFuture factory, ComponentDispatcher dispatcher) {
        super(pool, factory);
        this.dispatcher = dispatcher;
    }


    @Override
    public <T extends Event> CentralisedFuture<?> dispatch(UUID uuid, Supplier<T> event) {
        T eventObject = event.get();

        return composeArray(uuid).thenCompose(ids -> {

            Queue<UUID> queue = new ArrayDeque<>(Arrays.asList(ids));

            CentralisedFuture<?> dispatchOne = factory.completedFuture(Futures.COMPLETED);
            while (!queue.isEmpty()) {
                CentralisedFuture<?> finalDispatchOne = dispatchOne;
                dispatchOne = dispatchOne.thenCompose(ignored -> dispatcher.dispatch(queue.remove(), eventObject, finalDispatchOne));
            }

            return dispatchOne;
        });
    }

    CentralisedFuture<UUID[]> composeArray(UUID uuid) {

        return pool.get(uuid).thenCompose(ids -> {

            @SuppressWarnings("unchecked")
            CentralisedFuture<UUID[]>[] array = new CentralisedFuture[ids.length];

            for (int i = 0; i < ids.length; i++) {
                array[i] = composeArray(ids[i]);
            }

            return factory.allOf(array).thenApply(new ArrayFunc(ids, array));

        });
    }



}
