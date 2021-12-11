package com.superyuuki.yuukomponent.sql.impl;

import java.util.concurrent.CompletableFuture;

public interface Recursor {

    CompletableFuture<Integer[]> recursive();

}
