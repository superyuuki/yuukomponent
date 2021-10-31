package com.superyuuki.yuukomponent.core.centralized;

import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;

public interface StatSource {

    <T> Stat<T> make(Key<T> key);

}
