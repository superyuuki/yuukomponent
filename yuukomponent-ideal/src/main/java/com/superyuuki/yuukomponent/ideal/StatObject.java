package com.superyuuki.yuukomponent.ideal;

import java.util.function.Function;

public interface StatObject {

    void modify(String val, Function<Object, Object> function);

    Object value(String str);

}
