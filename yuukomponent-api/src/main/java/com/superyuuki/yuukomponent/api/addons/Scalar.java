package com.superyuuki.yuukomponent.api.addons;

public interface Scalar<T> {

    void load(T value);
    T retrieve();

}
