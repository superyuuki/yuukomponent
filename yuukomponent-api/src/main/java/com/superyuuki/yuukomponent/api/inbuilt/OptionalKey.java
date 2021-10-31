package com.superyuuki.yuukomponent.api.inbuilt;

public interface OptionalKey<T> {

    boolean present();
    T orDefault();

}
