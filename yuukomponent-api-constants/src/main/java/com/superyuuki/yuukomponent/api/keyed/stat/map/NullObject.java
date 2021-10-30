package com.superyuuki.yuukomponent.api.keyed.stat.map;

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
