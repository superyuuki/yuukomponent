package com.superyuuki.yuukomponent.api.keyed.stat.map;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.function.Function;

public interface StatObject extends Event {

    void modify(String val, Function<Object, Object> function);

    Object value(String str);

}
