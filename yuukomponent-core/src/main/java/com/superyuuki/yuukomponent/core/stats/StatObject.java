package com.superyuuki.yuukomponent.core.stats;

import com.superyuuki.yuukomponent.api.trait.Event;

import java.util.function.Function;

public interface StatObject extends Event {

    void modify(String val, Function<Object, Object> function);

    Object value(String str);

}
