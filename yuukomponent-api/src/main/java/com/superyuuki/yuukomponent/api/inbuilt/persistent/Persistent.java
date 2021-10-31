package com.superyuuki.yuukomponent.api.inbuilt.persistent;

public interface Persistent<T> {

    T val();
    void update(PersistentFunc<T> func);

}
