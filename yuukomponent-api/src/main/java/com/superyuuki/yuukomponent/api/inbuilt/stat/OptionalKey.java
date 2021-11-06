package com.superyuuki.yuukomponent.api.inbuilt.stat;

public interface OptionalKey<T> {

    boolean present();
    T orDefault();

}
