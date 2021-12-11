package com.superyuuki.yuukomponent.api.component.storage.internal;

import com.superyuuki.yuukomponent.api.trait.Trait;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface TraitStorage {

    CompletableFuture<Trait[]> retrieve(Integer[] tree);
}
