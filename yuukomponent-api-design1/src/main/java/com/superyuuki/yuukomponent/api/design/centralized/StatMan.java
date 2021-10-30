package com.superyuuki.yuukomponent.api.design.centralized;

import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;

import java.util.UUID;

public interface StatMan {

    <T> Stat<T> make(UUID componentId, Key<T> key);

}
