package com.superyuuki.yuukomponent.core.inbuilt.stat.object;

import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;

import java.util.function.Function;

public class NullObject implements StatObject {

    //impl later

    @Override
    public void modify(String val, Function<Object, Object> function) {

    }

    @Override
    public Object value(String str) {
        return null;
    }
}
