package com.superyuuki.yuukomponent.sql.newtype;

import com.superyuuki.yuukomponent.api.component.storage.Tree;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ReduceMap {

    CompletableFuture<Map<Integer, Tree>> reduce();

}
