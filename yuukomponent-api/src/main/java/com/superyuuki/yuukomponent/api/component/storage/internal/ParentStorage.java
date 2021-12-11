package com.superyuuki.yuukomponent.api.component.storage.internal;

import java.util.concurrent.CompletableFuture;

public interface ParentStorage {

    CompletableFuture<Integer> parent(int child);

}
