package com.superyuuki.yuukomponent.api.keyed.persistent;

public interface Persistent<T> {

    T val();
    void update(PersistentFunc<T> func);

}
