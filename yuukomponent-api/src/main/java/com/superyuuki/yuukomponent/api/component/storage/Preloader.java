package com.superyuuki.yuukomponent.api.component.storage;

import com.superyuuki.yuukomponent.api.component.storage.internal.StructureStorage;
import com.superyuuki.yuukomponent.api.component.storage.internal.TraitStorage;

import java.util.concurrent.CompletableFuture;

public interface Preloader {

    /**
     * Attempts to load values into {@link StructureStorage} and {@link TraitStorage} if they aren't already present.
     * @param components the root ids to look for
     * @return
     */
    CompletableFuture<?> preload(Integer... components);

}
