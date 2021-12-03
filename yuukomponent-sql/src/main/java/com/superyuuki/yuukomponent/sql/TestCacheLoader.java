package com.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class TestCacheLoader implements AsyncCacheLoader<Integer, List<Integer>> {

    private final FactoryOfTheFuture factory;
    private final Driver driver;

    public TestCacheLoader(FactoryOfTheFuture factory, Driver driver) {
        this.factory = factory;
        this.driver = driver;
    }

    @Override
    public CompletableFuture<? extends List<Integer>> asyncLoad(Integer key, Executor executor) throws Exception {
        throw new IllegalStateException("Should not be invoked as values should be precached!");
    }

    @Override
    public CompletableFuture<? extends Map<? extends Integer, ? extends List<Integer>>> asyncLoadAll(Set<? extends Integer> keys, Executor executor) throws Exception {
        if (keys.size() > 1) throw new IllegalStateException("Cannot use asyncLoadAll in TestCacheLoader with more than one element!");

        for (Integer integer : keys) {
            return factory.supplyAsync(() -> driver.allChildren(integer)); //dump first
        }

        throw new IllegalStateException("No key in set!");
    }
}
