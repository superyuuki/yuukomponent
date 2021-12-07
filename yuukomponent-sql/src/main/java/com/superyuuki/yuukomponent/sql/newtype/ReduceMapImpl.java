package com.superyuuki.yuukomponent.sql.newtype;

import com.superyuuki.yuukomponent.api.component.storage.Tree;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ReduceMapImpl implements ReduceMap {

    private final Map<Integer, CompletableFuture<Tree>> proxies;

    public ReduceMapImpl(Map<Integer, CompletableFuture<Tree>> proxies) {
        this.proxies = proxies;
    }

    @Override
    public CompletableFuture<Map<Integer, Tree>> reduce() {

        if (proxies.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyMap());
        }

        CompletableFuture<?>[] array = proxies.values().toArray(new CompletableFuture[0]);
        return CompletableFuture.allOf(array).thenApply(ignored -> {
            var result = new LinkedHashMap<Integer, Tree>(proxies.size());
            proxies.forEach((key, future) -> {
                Tree value = future.getNow(null);
                if (value != null) {
                    result.put(key, value);
                }
            });
            return Collections.unmodifiableMap(result);
        });
    }
}
