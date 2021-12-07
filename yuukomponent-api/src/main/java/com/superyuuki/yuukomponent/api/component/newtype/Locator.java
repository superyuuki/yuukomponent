package com.superyuuki.yuukomponent.api.component.newtype;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface Locator {

    CompletableFuture<List<Integer>> withDescendants(int parent);
    CompletableFuture<List<Integer>> withChildren(int parent);
    CompletableFuture<List<Integer>> fromRoot(int child); //query
    CompletableFuture<List<Integer>> fromRootChildren(int child);
    CompletableFuture<List<Integer>> fromGlobal(); //query every single root component
    CompletableFuture<Boolean> add(int child, int parent);
    CompletableFuture<Boolean> remove(int child, int parent);
    CompletableFuture<Boolean> replace(int parent, int remove, int add);

}
