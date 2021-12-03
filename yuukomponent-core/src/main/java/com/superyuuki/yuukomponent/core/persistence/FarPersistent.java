package com.superyuuki.yuukomponent.core.persistence;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

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

    CentralisedFuture<T> update(UnaryOperator<T> operator);
    CentralisedFuture<T> query();

}
