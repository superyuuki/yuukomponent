package com.superyuuki.yuukomponent.api.component.newtype;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.UUID;

public interface Locator {

    CentralisedFuture<List<Integer>> withDescendants(int parent);
    CentralisedFuture<List<Integer>> withChildren(int parent);
    CentralisedFuture<List<Integer>> fromRoot(int child); //query
    CentralisedFuture<List<Integer>> fromRootChildren(int child);
    CentralisedFuture<List<Integer>> fromGlobal(); //query every single root component
    CentralisedFuture<Boolean> add(int child, int parent);
    CentralisedFuture<Boolean> remove(int child, int parent);
    CentralisedFuture<Boolean> replace(int parent, int remove, int add);

}
