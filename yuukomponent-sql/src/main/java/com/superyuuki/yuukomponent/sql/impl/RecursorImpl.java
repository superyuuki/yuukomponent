package com.superyuuki.yuukomponent.sql.impl;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.sql.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecursorImpl implements Recursor {

    private final int topLevel;
    private final CompletableFuture<Integer[]> firstFuture;
    private final AsyncCache<Integer, Integer[]> cache;
    private final Driver driver;

    public RecursorImpl(int topLevel, CompletableFuture<Integer[]> firstFuture, AsyncCache<Integer, Integer[]> cache, Driver driver) {
        this.topLevel = topLevel;
        this.firstFuture = firstFuture;
        this.cache = cache;
        this.driver = driver;
    }

    @Override
    public CompletableFuture<Integer[]> recursive() {
        return firstFuture.thenCompose(selfChildren -> {

            List<CompletableFuture<Integer[]>> waitingOnChildrenToLoad = new ArrayList<>();

            for (int child : selfChildren) {
                waitingOnChildrenToLoad.add(recurse(child)); //oh boy fuck me in the ass
            }

            //more array conversions when i'm not writing frames
            return CompletableFuture.allOf(waitingOnChildrenToLoad.toArray(CompletableFuture[]::new)).thenApply(ignored -> {
                List<Integer> childrenAreLoadedLetsReturn = new ArrayList<>();
                childrenAreLoadedLetsReturn.add(topLevel); //include self at the base of this tree representation

                for (CompletableFuture<Integer[]> future : waitingOnChildrenToLoad) {
                    //this is why i want to stick to arrays, ugh
                    childrenAreLoadedLetsReturn.addAll(List.of(future.join()));
                }

                //array
                return childrenAreLoadedLetsReturn.toArray(Integer[]::new);
            });
        });
    }

    protected CompletableFuture<Integer[]> recurse(int key) {

        return cache.get(key, driver::loadChildren).thenCompose(selfChildren -> {

            List<CompletableFuture<Integer[]>> waitingOnChildrenToLoad = new ArrayList<>();

            for (int child : selfChildren) {
                waitingOnChildrenToLoad.add(recurse(child)); //oh boy fuck me in the ass
            }

            //more array conversions when i'm not writing frames
            return CompletableFuture.allOf(waitingOnChildrenToLoad.toArray(CompletableFuture[]::new)).thenApply(ignored -> {
                List<Integer> childrenAreLoadedLetsReturn = new ArrayList<>();
                childrenAreLoadedLetsReturn.add(key); //include self at the base of this tree representation

                for (CompletableFuture<Integer[]> future : waitingOnChildrenToLoad) {
                    //this is why i want to stick to arrays, ugh
                    childrenAreLoadedLetsReturn.addAll(List.of(future.join()));
                }

                //array
                return childrenAreLoadedLetsReturn.toArray(Integer[]::new);
            });
        });
    }



}
