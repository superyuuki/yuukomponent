package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.trait.Event;
import com.superyuuki.yuukomponent.api.trait.Trait;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface InternalDispatch {

    CompletableFuture<?> dispatch(Supplier<Event> supplier, CompletableFuture<Trait[]> traits);

}
