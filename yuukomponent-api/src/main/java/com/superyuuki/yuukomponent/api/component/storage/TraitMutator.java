package com.superyuuki.yuukomponent.api.component.storage;

import java.util.concurrent.CompletableFuture;

public interface TraitMutator {

    CompletableFuture<?> destroy(int component);

}
