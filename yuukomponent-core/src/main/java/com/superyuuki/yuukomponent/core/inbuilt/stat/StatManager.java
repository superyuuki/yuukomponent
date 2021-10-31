package com.superyuuki.yuukomponent.core.inbuilt.stat;

import com.superyuuki.yuukomponent.api.inbuilt.Key;
import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;

import java.util.UUID;

/**
 * Technically should be it's own behavior package
 */
public interface StatManager {

    <T> Stat<T> retrieve(UUID uuid, Key<T> key);

    StatSource retrieveFor(UUID uuid);

}
