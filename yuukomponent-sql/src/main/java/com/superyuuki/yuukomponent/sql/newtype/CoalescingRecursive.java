package com.superyuuki.yuukomponent.sql.newtype;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.storage.Children;
import com.superyuuki.yuukomponent.api.component.storage.Recursive;
import com.superyuuki.yuukomponent.api.component.storage.Tree;
import com.superyuuki.yuukomponent.sql.Driver;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class CoalescingRecursive implements Recursive {

    private final Driver driver;
    private final AsyncCache<Integer, Children> cache;

    public CoalescingRecursive(Driver driver, AsyncCache<Integer, Children> cache) {
        this.driver = driver;
        this.cache = cache;
    }

    @Override
    public CompletableFuture<Map<Integer, Tree>> get(Collection<Integer> rootNodes) {

        //scoped (threadsafe)
        var computedFutures = new HashMap<Integer, CompletableFuture<Tree>>(); //the result! Woot!
        var toLoad = new HashMap<Integer, CompletableFuture<Children>>();

        boolean needToCallDriver = false;

        for (Integer key : rootNodes) {
            CompletableFuture<Tree> returnableProxy = new CompletableFuture<>();

            CompletableFuture<Children> putProxy = new CompletableFuture<>();
            CompletableFuture<Children> mapReturn = cache.asMap().put(key, putProxy);

            if (mapReturn == null) { //missing, please load
                needToCallDriver = true;

                toLoad.put(key, putProxy);
            } else {
                returnableProxy = new RecursorImpl(cache, driver).recurse(key);
            }
            computedFutures.put(key, returnableProxy);
        }

        if (!needToCallDriver) {
            return new ReduceMapImpl(computedFutures).reduce();
        }


        return CompletableFuture.runAsync(() -> {

            var closeMap = driver.loadTrees(toLoad.keySet()); //immutable
            for (Map.Entry<Integer, Children> entry : closeMap.entrySet()) {

                int id = entry.getKey();
                Children child = entry.getValue();

                //check whether it is a root or not
                CompletableFuture<Children> nullable = toLoad.get(id);

                if (nullable != null) { //if this is a root node we requested to load, modify the existing future!
                    //This is a proxy!

                    if (child == null) throw new NoSuchComponentException(id); //expected root

                    Recursive rec = new Recursive(closeMap);

                    rec.recursive(id);

                    nullable.complete(child); //mark child as complete, value is now cached.
                    computedFutures.get(id).complete(rec.build());
                } else {

                    if (child == null) {
                        cache.asMap().remove(id);
                    } else {
                        //just update old values
                        cache.put(id, CompletableFuture.completedFuture(child));
                    }
                }
            }

            //now we want to build our trees!

            //all trees should be constructed by here. cached trees are constructed in the first for loop
            //and are populated by a recursive future builder optimized for cached values (if not cached,
            //it needs to make a getChildren call for every children, making it o(recursions(n)) operation.
            //all non-cached values are assumed to not have children in the cache, and their trees are created
            //directly from database values.

        }).thenCompose(ignored -> new ReduceMapImpl(computedFutures).reduce());

    }

    static class Recursive {

        private final List<Integer> builtList = new ArrayList<>();

        private final Map<Integer, Children> map;

        Recursive(Map<Integer, Children> map) {
            this.map = map;
        }

        void recursive(Integer select) {
            builtList.add(select);

            Children nb = map.get(select);
            if (nb == null) throw new NoSuchComponentException(select);

            for (Integer i : nb.directDescendants()) {
                recursive(select);
            }
        }

        Tree build() {
            return new Tree(builtList.toArray(new Integer[0]));
        }

    }


}
