package com.superyuuki.yuukomponent.ideal.so;

import com.superyuuki.yuukomponent.ideal.StatObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EmptyObject implements StatObject {

    private final Map<String, Object> map = new HashMap<>();

    @Override
    public void modify(String val, Function<Object, Object> function) {
        map.compute(val, (a,b) -> function.apply(b));
    }

    @Override
    public Object value(String str) {
        return map.get(str);
    }
}
