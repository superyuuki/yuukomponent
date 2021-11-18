package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.component.Component;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.*;

/**
 * Component execution is considered completed when
 *
 * 1. parent component (represented by top)'s execution is finished
 * 2. all mutators are finished computing the event
 */
public class ParallelComponent implements Component {

    private final FactoryOfTheFuture factory;
    private final Collection<Mutator> mutators;

    public ParallelComponent(FactoryOfTheFuture factory, Collection<Mutator> mutators) {
        this.factory = factory;
        this.mutators = mutators;
    }

    @Override
    public <T extends Event> CentralisedFuture<?> compute(T event, CentralisedFuture<?> top) {
        List<CentralisedFuture<?>> futures = new ArrayList<>();

        for (Mutator mutator : mutators) {
            futures.add(mutator.insertBehavior(top, event));
        }

        return top.thenCompose(ignored -> factory.allOf(futures.toArray(CentralisedFuture[]::new))); //any component after here has to wait for this component to finish executing
    }
}
