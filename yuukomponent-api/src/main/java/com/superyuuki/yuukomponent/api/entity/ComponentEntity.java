package com.superyuuki.yuukomponent.api.entity;

import com.superyuuki.yuukomponent.api.keyed.persistent.PersistentSource;
import com.superyuuki.yuukomponent.api.keyed.stat.StatSource;

public interface ComponentEntity {

    StatSource calculateStat();
    PersistentSource calculatePers();

}
