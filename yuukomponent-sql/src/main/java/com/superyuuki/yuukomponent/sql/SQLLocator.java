package com.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.newtype.Locator;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SQLLocator implements Locator {

    private final Driver driver;

    private final AsyncCache<Integer, List<Integer>> cache = Caffeine.newBuilder().buildAsync();

    public SQLLocator(Driver driver) {
        this.driver = driver;
    }

    @Override
    public CompletableFuture<List<Integer>> withDescendants(int parent) {
        return preload(parent).thenCompose(ignored -> recursive(parent));
    }

    public CompletableFuture<?> preload(Integer integer) {
        return cache.getAll(List.of(integer), (s, e) -> {
            return CompletableFuture.supplyAsync(() -> driver.loadTrees(integer), e); //load every required resource into cache
        });
    }

    public CompletableFuture<List<Integer>> recursive(int top) {
        return cache.get(top, (s, e) -> { //should never be called since recursive will be invoked after a getAll
            throw new IllegalStateException("Could not load supposedly cached value from cache!");
            //return factory.supplyAsync(() -> driver.immediateChildren(top));
        }).thenCompose(li -> {

            List<CompletableFuture<List<Integer>>> col = new ArrayList<>();

            for (Integer held : li) {
                col.add(recursive(held));
            }

            return CompletableFuture.allOf(col.toArray(CompletableFuture[]::new)).thenApply(ignored -> {
                List<Integer> toReturn = new ArrayList<>();
                toReturn.add(top);

                for (CompletableFuture<List<Integer>> subfuture : col) {
                    toReturn.addAll(subfuture.join());
                }

                return toReturn;
            });

        });
    }

    @Override
    public CompletableFuture<List<Integer>> withChildren(int parent) {
        return null;
    }

    @Override
    public CompletableFuture<List<Integer>> fromRoot(int child) {
        return null;
    }

    @Override
    public CompletableFuture<List<Integer>> fromRootChildren(int child) {
        return null;
    }

    @Override
    public CompletableFuture<List<Integer>> fromGlobal() {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> add(int child, int parent) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> remove(int child, int parent) {
        return null;
    }

    @Override
    public CompletableFuture<Boolean> replace(int parent, int remove, int add) {
        return null;
    }
}
