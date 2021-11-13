package com.superyuuki.yuukomponent.persistence;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.function.UnaryOperator;

public interface LocalPersistent<T> {

    CentralisedFuture<Void> set(T value);
    CentralisedFuture<Void> update(UnaryOperator<T> operator);
    T query();

}
