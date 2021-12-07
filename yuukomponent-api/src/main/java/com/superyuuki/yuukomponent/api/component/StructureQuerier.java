package com.superyuuki.yuukomponent.api.component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface StructureQuerier {


    CompletableFuture<List<UUID>> ordered(int id);
    CompletableFuture<List<UUID>> orderedImmediate(int id);
    CompletableFuture<List<UUID>> orderedFromRoot(int id);
    CompletableFuture<List<UUID>> orderedImmediateFromRoot(int id);

    CompletableFuture<List<UUID>> all();

}
