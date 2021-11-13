package com.superyuuki.yuukomponent.persistence;

public interface CachingPersistent<T> extends Persistent<T> {

    T cachedValue();

}
