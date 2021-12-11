package com.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.api.component.storage.internal.TraitStorage;
import com.superyuuki.yuukomponent.api.trait.Trait;
import com.superyuuki.yuukomponent.api.trait.TraitFactory;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class TraitStorageSql implements TraitStorage {

    private final AsyncCache<Integer, Trait> cache;
    private final Driver driver;
    private final TraitFactory factory;

    public TraitStorageSql(AsyncCache<Integer, Trait> cache, Driver driver, TraitFactory factory) {
        this.cache = cache;
        this.driver = driver;
        this.factory = factory;
    }

    @Override
    public CompletableFuture<Trait[]> retrieve(Integer[] tree) {
        return cache.getAll(Arrays.stream(tree).toList(), set -> {

            Map<Integer, String> firstMap = driver.loadTypes(Set.copyOf(set));
            Map<Integer, Trait> returnMap = new HashMap<>();

            for (Map.Entry<Integer, String> entry : firstMap.entrySet()) {
                returnMap.put(
                        entry.getKey(),
                        factory.createOfType(
                                entry.getValue()
                        )
                );
            }

            return returnMap;

        }).thenApply(mp -> {
            List<Trait> orderedTraits = new ArrayList<>();

            for (Integer key : tree) {
                orderedTraits.add(mp.get(key));
            }

            return orderedTraits.toArray(Trait[]::new);
        });
    }
}
