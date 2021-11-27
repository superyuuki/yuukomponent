package com.superyuuki.yuukomponent.persistence;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.function.UnaryOperator;

/**
 * Threadsafe non-locking (impl) persistence object that is stored in memory and is acceptable for
 * things that need to be accessed frequently with low regards for data security (e.g. if the platform crashes,
 * we don't care if the amount of bullets in your gun is one more than it should be since a transaction wasn't completed
 * in time for a shutdown signal)
 *
 * Good uses: A gun's ammo counter
 * Bad uses: Slots
 *
 * @param <T> the value
 */
public interface LocalPersistent<T> {

    void update(UnaryOperator<T> operator);
    T query();

}
