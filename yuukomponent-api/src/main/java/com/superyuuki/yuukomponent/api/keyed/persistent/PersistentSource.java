package com.superyuuki.yuukomponent.api.keyed.persistent;

import com.superyuuki.yuukomponent.api.keyed.Key;

/**
 * A source of persistents tied to a component entity
 */
public interface PersistentSource {

    <T> Persistent<T> of(Key<T> key);

}
