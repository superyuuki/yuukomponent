package com.superyuuki.yuukomponent.sql.impl;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface ReduceMap {

    CompletableFuture<Map<Integer, Integer[]>> reduce();

}
