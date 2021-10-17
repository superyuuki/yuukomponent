package com.superyuuki.yuukomponent.api.keyed.stat;

import com.superyuuki.yuukomponent.api.keyed.Key;

/**
 * A source of stats tied to a component calculation chain
 */
public interface StatSource {

    <T> Stat<T> stat(Key<T> key);
    <T> OptionalStat<T> opt(Key<T> key);


}
