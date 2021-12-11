package com.superyuuki.yuukomponent.api.component.storage;

import com.superyuuki.yuukomponent.api.trait.Trait;

import java.util.concurrent.CompletableFuture;

public interface Loader {

    CompletableFuture<Boolean> check(int root);
    CompletableFuture<Boolean> indexedCheck(int rootOrChild);

    /**
     * Get trait stack from root node
     * @param root the root node
     * @return the trait stack
     */
    CompletableFuture<Trait[]> load(int root);

    /**
     * Get trait stack from root node. If root is not present tries to find a root node to execute with by searching
     * all root nodes for the queried node as a child node.
     *
     * @param rootOrChild the root node or child node
     * @return the trait stack
     */
    CompletableFuture<Trait[]> indexedLoad(int rootOrChild);

}
