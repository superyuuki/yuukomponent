package com.superyuuki.yuukomponent.core.inbuilt.stat;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;

public interface StatSource {

    <T> Stat<T> make(Key<T> key);

}
