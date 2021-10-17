package com.superyuuki.yuukomponent.core.test;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.BehaviorLoader;
import com.superyuuki.yuukomponent.api.keyed.Key;
import com.superyuuki.yuukomponent.api.keyed.persistent.PersistentSource;
import com.superyuuki.yuukomponent.api.keyed.stat.StatSource;

public class TestLoader implements BehaviorLoader {

    static final Key<Integer> DAMAGE = new Key<>("test_damage", Integer.class);
    static final Key<Integer> ROF = new Key<>("test_rof", Integer.class);
    static final Key<Integer> AMMO = new Key<>("test_ammo", Integer.class);

    @Override
    public Behavior load(PersistentSource persistentSource, StatSource statSource) {
        return new TestBehavior(
                statSource.stat(DAMAGE),
                statSource.stat(ROF),
                persistentSource.of(AMMO)
        );
    }
}
