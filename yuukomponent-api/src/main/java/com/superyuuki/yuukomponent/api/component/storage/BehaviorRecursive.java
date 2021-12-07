package com.superyuuki.yuukomponent.api.component.storage;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface BehaviorRecursive {

    CompletableFuture<Map<Integer, DispatchTree>> convert(Map<Integer, Tree> map);

}
