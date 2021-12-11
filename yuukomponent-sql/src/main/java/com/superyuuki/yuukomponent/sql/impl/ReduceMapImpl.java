package com.superyuuki.yuukomponent.sql.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ReduceMapImpl implements ReduceMap {

    private final Map<Integer, CompletableFuture<Integer[]>> proxies;

    public ReduceMapImpl(Map<Integer, CompletableFuture<Integer[]>> proxies) {
        this.proxies = proxies;
    }

    @Override
    public CompletableFuture<Map<Integer, Integer[]>> reduce() {

        if (proxies.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyMap());
        }

        CompletableFuture<?>[] array = proxies.values().toArray(new CompletableFuture[0]);
        return CompletableFuture.allOf(array).thenApply(ignored -> {
            var result = new LinkedHashMap<Integer, Integer[]>(proxies.size());
            proxies.forEach((key, future) -> {
                Integer[] value = future.getNow(null);
                if (value != null) {
                    result.put(key, value);
                }
            });
            return Collections.unmodifiableMap(result);
        });
    }
}
