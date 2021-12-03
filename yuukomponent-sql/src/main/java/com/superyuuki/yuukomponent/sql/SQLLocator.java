package com.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.newtype.Locator;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;

public class SQLLocator implements Locator {

    private final FactoryOfTheFuture factory;
    private final Driver driver;

    private final AsyncCache<Integer, List<Integer>> cache = Caffeine.newBuilder().buildAsync();

    public SQLLocator(FactoryOfTheFuture factory, Driver driver) {
        this.factory = factory;
        this.driver = driver;
    }



    @Override
    public CentralisedFuture<List<Integer>> withDescendants(int parent) {
        return preload(parent).thenCompose(ignored -> recursive(parent));
    }

    public CentralisedFuture<?> preload(Integer integer) {
        return factory.copyFuture(cache.getAll(List.of(integer), (s, e) -> {
            return factory.supplyAsync(() -> driver.allChildren(integer)); //load every required resource into cache
        }));
    }

    public CentralisedFuture<List<Integer>> recursive(int top) {
        return (CentralisedFuture<List<Integer>>) cache.get(top, (s, e) -> { //should never be called since recursive will be invoked after a getAll
            throw new IllegalStateException("Could not load supposedly cached value from cache!");
            //return factory.supplyAsync(() -> driver.immediateChildren(top));
        }).thenCompose(li -> {

            List<CentralisedFuture<List<Integer>>> col = new ArrayList<>();

            for (Integer held : li) {
                col.add(recursive(held));
            }

            return factory.allOf(col).thenApply(ignored -> {
                List<Integer> toReturn = new ArrayList<>();
                toReturn.add(top);

                for (CentralisedFuture<List<Integer>> subfuture : col) {
                    toReturn.addAll(subfuture.join());
                }

                return toReturn;
            });

        });
    }

    @Override
    public CentralisedFuture<List<Integer>> withChildren(int parent) {
        return null;
    }

    @Override
    public CentralisedFuture<List<Integer>> fromRoot(int child) {
        return null;
    }

    @Override
    public CentralisedFuture<List<Integer>> fromRootChildren(int child) {
        return null;
    }

    @Override
    public CentralisedFuture<List<Integer>> fromGlobal() {
        return null;
    }

    @Override
    public CentralisedFuture<Boolean> add(int child, int parent) {
        return null;
    }

    @Override
    public CentralisedFuture<Boolean> remove(int child, int parent) {
        return null;
    }

    @Override
    public CentralisedFuture<Boolean> replace(int parent, int remove, int add) {
        return null;
    }
}
