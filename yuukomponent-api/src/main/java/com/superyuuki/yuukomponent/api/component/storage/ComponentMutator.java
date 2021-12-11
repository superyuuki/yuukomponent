package com.superyuuki.yuukomponent.api.component.storage;

import java.util.concurrent.CompletableFuture;

public interface ComponentMutator {

    CompletableFuture<?> add(int parent, int child);
    CompletableFuture<?> remove(int parent, int child);
    CompletableFuture<?> replace(int parent, int previous, int next);

}
