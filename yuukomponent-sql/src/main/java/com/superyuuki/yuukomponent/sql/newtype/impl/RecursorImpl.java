package com.superyuuki.yuukomponent.sql.newtype.impl;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.api.component.storage.Children;
import com.superyuuki.yuukomponent.api.component.storage.Tree;
import com.superyuuki.yuukomponent.sql.Driver;
import com.superyuuki.yuukomponent.sql.newtype.contract.Recursor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class RecursorImpl implements Recursor {

    private final int topLevel;
    private final CompletableFuture<Children> firstFuture;
    private final AsyncCache<Integer, Children> cache;
    private final Driver driver;

    public RecursorImpl(int topLevel, CompletableFuture<Children> firstFuture, AsyncCache<Integer, Children> cache, Driver driver) {
        this.topLevel = topLevel;
        this.firstFuture = firstFuture;
        this.cache = cache;
        this.driver = driver;
    }

    @Override
    public CompletableFuture<Tree> recursive() {
        return firstFuture.thenCompose(selfChildren -> {

            List<CompletableFuture<Tree>> waitingOnChildrenToLoad = new ArrayList<>();

            for (int child : selfChildren.directDescendants()) {
                waitingOnChildrenToLoad.add(recurse(child)); //oh boy fuck me in the ass
            }

            //more array conversions when i'm not writing frames
            return CompletableFuture.allOf(waitingOnChildrenToLoad.toArray(CompletableFuture[]::new)).thenApply(ignored -> {
                List<Integer> childrenAreLoadedLetsReturn = new ArrayList<>();
                childrenAreLoadedLetsReturn.add(topLevel); //include self at the base of this tree representation

                for (CompletableFuture<Tree> future : waitingOnChildrenToLoad) {
                    //this is why i want to stick to arrays, ugh
                    childrenAreLoadedLetsReturn.addAll(List.of(future.join().dispatchOrder()));
                }

                //array
                return new Tree(childrenAreLoadedLetsReturn.toArray(Integer[]::new));
            });
        });
    }

    protected CompletableFuture<Tree> recurse(int key) {

        return cache.get(key, driver::loadChildren).thenCompose(selfChildren -> {

            List<CompletableFuture<Tree>> waitingOnChildrenToLoad = new ArrayList<>();

            for (int child : selfChildren.directDescendants()) {
                waitingOnChildrenToLoad.add(recurse(child)); //oh boy fuck me in the ass
            }

            //more array conversions when i'm not writing frames
            return CompletableFuture.allOf(waitingOnChildrenToLoad.toArray(CompletableFuture[]::new)).thenApply(ignored -> {
                List<Integer> childrenAreLoadedLetsReturn = new ArrayList<>();
                childrenAreLoadedLetsReturn.add(key); //include self at the base of this tree representation

                for (CompletableFuture<Tree> future : waitingOnChildrenToLoad) {
                    //this is why i want to stick to arrays, ugh
                    childrenAreLoadedLetsReturn.addAll(List.of(future.join().dispatchOrder()));
                }

                //array
                return new Tree(childrenAreLoadedLetsReturn.toArray(Integer[]::new));
            });
        });
    }



}
