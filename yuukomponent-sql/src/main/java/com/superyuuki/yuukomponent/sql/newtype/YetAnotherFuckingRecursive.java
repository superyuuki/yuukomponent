package com.superyuuki.yuukomponent.sql.newtype;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.superyuuki.yuukomponent.api.component.storage.Children;
import com.superyuuki.yuukomponent.api.component.storage.Recursive;
import com.superyuuki.yuukomponent.api.component.storage.Tree;
import com.superyuuki.yuukomponent.sql.Driver;
import com.superyuuki.yuukomponent.sql.newtype.impl.RecursorImpl;
import com.superyuuki.yuukomponent.sql.newtype.impl.LocalRecursorImpl;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class YetAnotherFuckingRecursive implements Recursive {

    private final AsyncCache<Integer, Children> cache;
    private final Driver driver;

    public YetAnotherFuckingRecursive(AsyncCache<Integer, Children> cache, Driver driver) {
        this.cache = cache;
        this.driver = driver;
    }

    public CompletableFuture<Tree> get(Integer key) {
        CompletableFuture<Children> placeholder = new CompletableFuture<>();
        CompletableFuture<Children> oldFuture = cache.asMap().putIfAbsent(key, placeholder);

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

    @Override
    public CompletableFuture<Map<Integer, Tree>> get(Collection<Integer> keys) {
        Map<Integer, CompletableFuture<Children>> toLoad = new HashMap<>();

        Map<Integer, CompletableFuture<Tree>> toReturn = new HashMap<>();

        for (int i : keys) {

            //an actual value
            CompletableFuture<Children> placeholder = new CompletableFuture<>();

            //using null as a return signal
            CompletableFuture<Children> oldFuture = cache.asMap().putIfAbsent(i, placeholder);

            if (oldFuture == null) {
                //it is time to mark it for loading...

                toLoad.put(i, placeholder);
            } else {
                //already present (loading) mark it for return

                toReturn.put(
                        i,
                        new RecursorImpl(i, oldFuture, cache, driver).recursive()
                ); //no need to add to loading since the loading will be done by the recursor which expects
                   // local values to already be cached (if they are evicted it will do a manual load)
            }
        }

        //ok time to load

        if (toLoad.isEmpty()) {
            return new ReduceMapImpl(toReturn).reduce();
        }  //ugh, we have to load.

        return CompletableFuture.supplyAsync(() -> {

            var map = driver.loadTrees(keys);

            //lets do some cache inserts!!! (or cache updates if values are already marked as
            // being loaded into the cache)
            map.forEach((i,c) -> {

                var nullableFuture = toLoad.get(i);

                if (nullableFuture != null) { //oh, it's marked as a preloaded one we can complete!
                    nullableFuture.obtrudeValue(c);
                } else {
                    //damn, just insert
                    cache.put(i, CompletableFuture.completedFuture(c));
                }
            });

            //cache should be updated! Now, let's access our driver's map to return proper tree stuff!

            Map<Integer, CompletableFuture<Tree>>

        })

    }

}
