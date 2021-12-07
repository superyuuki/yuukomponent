package com.superyuuki.yuukomponent.core.persistence;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

/**
 * Threadsafe persistence object that typically is backed by a structured database and can be used for secure data.
 * Should not be used for things queried often.
 *
 * Good uses: Slots, things that are really important
 * Bad uses: Things that are queried often
 *
 * @param <T>
 */
public interface FarPersistent<T> {

    CompletableFuture<T> update(UnaryOperator<T> operator);
    CompletableFuture<T> query();

}
