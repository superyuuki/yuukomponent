package com.superyuuki.yuukomponent.sql.newtype.contract;

import com.superyuuki.yuukomponent.api.component.storage.Tree;

import java.util.concurrent.CompletableFuture;

public interface Recursor {

    CompletableFuture<Tree> recursive();

}
