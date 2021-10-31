package com.superyuuki.yuukomponent.core.centralized;

import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;

import java.util.UUID;

public interface StatManager {

    <T> Stat<T> retrieve(UUID uuid, Key<T> key);

}
