package com.superyuuki.yuukomponent.ideal;

import java.util.function.Function;

public class EmptyObject implements StatObject {
    @Override
    public void modify(String val, Function<Object, Object> function) {

    }

    @Override
    public Object value(String str) {
        return null;
    }
}
