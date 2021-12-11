package com.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.api.component.storage.internal.StructureStorage;
import com.superyuuki.yuukomponent.sql.impl.LocalRecursorImpl;
import com.superyuuki.yuukomponent.sql.impl.RecursorImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class StructureStorageSql implements StructureStorage {

    private final AsyncCache<Integer, Integer[]> cache;
    private final Driver driver;

    public StructureStorageSql(AsyncCache<Integer, Integer[]> cache, Driver driver) {
        this.cache = cache;
        this.driver = driver;
    }

    public CompletableFuture<Integer[]> getDescendants(Integer key) {
        CompletableFuture<Integer[]> placeholder = new CompletableFuture<>();
        CompletableFuture<Integer[]> oldFuture = cache.asMap().putIfAbsent(key, placeholder);

        if (oldFuture == null) {
            //nothing was present...

            return CompletableFuture.supplyAsync(() -> {

                var map = driver.loadTrees(List.of(key));

                map.forEach((i,c) -> {
                    if (i.equals(key)) {
                        placeholder.obtrudeValue(c);
                    } else {
                        cache.put(i, CompletableFuture.completedFuture(c));
                    }
                });

                return new LocalRecursorImpl(map, key).recursive();
            });

        } else {
            //already being loaded...

            return new RecursorImpl(key, oldFuture, cache, driver).recursive();
        }
    }

}
