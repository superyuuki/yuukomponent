package com.superyuuki.yuukomponent.api.component.storage;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface Recursive {

    CompletableFuture<Tree> get(Integer key);

}
