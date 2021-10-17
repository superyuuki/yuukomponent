package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.keyed.PersistentSource;
import com.superyuuki.yuukomponent.api.keyed.persistent.PersistentSource;
import com.superyuuki.yuukomponent.api.keyed.stat.StatSource;

public interface BehaviorLoader {

    Behavior load(PersistentSource persistentSource, StatSource statSource);

}
