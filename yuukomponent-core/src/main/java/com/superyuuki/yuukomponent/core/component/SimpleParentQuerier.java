package com.superyuuki.yuukomponent.core.component;

import com.superyuuki.yuukomponent.api.component.ParentQuerier;
import com.superyuuki.yuukomponent.api.component.low.StructPool;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.List;
import java.util.UUID;

public class SimpleParentQuerier implements ParentQuerier {

    private final StructPool pool;
    private final FactoryOfTheFuture factory;

    public SimpleParentQuerier(StructPool pool, FactoryOfTheFuture factory) {
        this.pool = pool;
        this.factory = factory;
    }


    @Override
    public CentralisedFuture<List<UUID>> withoutParents() {
        return factory.supplyAsync(pool::allWithoutParents);
    }
}
