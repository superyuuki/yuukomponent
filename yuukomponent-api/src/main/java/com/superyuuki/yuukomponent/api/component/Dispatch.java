package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.trait.Event;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface Dispatch {

    CompletableFuture<?> dispatch(Supplier<Event> eventSupplier);

}
