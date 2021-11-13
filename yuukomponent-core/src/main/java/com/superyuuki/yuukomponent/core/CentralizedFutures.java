package com.superyuuki.yuukomponent.core;

import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

public class CentralizedFutures {

    private final FactoryOfTheFuture factory;

    public CentralizedFutures(FactoryOfTheFuture factory) {
        this.factory = factory;
    }

    public <T> CentralisedFuture<List<T>> allAsList(
            List<? extends CentralisedFuture<? extends T>> stages) {

        @SuppressWarnings("unchecked") // generic array creation
        final CentralisedFuture<? extends T>[] all = new CentralisedFuture[stages.size()];
        for (int i = 0; i < stages.size(); i++) {
            all[i] = stages.get(i).toCompletableFuture();
        }

        CentralisedFuture<?> allOf = factory.allOf(all);

        for (CentralisedFuture<? extends T> centralisedFuture : all) {
            centralisedFuture.exceptionally(throwable -> {
                if (!allOf.isDone()) {
                    allOf.completeExceptionally(throwable);
                }
                return null;
            });
        }

        return allOf.thenApply(ignored -> {
                    final List<T> result = new ArrayList<>(all.length);
                    for (CentralisedFuture<? extends T> centralisedFuture : all) {
                        result.add(centralisedFuture.join());
                    }
                    return result;
                });

    }



}