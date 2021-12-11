package com.superyuuki.yuukomponent.api.component.storage.internal;

import java.util.concurrent.CompletableFuture;

public interface StructureStorage {

    CompletableFuture<Integer[]> getDescendants(Integer key);

}
