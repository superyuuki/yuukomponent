package com.superyuuki.yuukomponent.core.test;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.keyed.persistent.Persistent;
import com.superyuuki.yuukomponent.api.keyed.stat.Stat;

public class TestBehavior implements Behavior {

    private final Stat<Integer> damage;
    private final Stat<Integer> rof;
    private final Persistent<Integer> ammo;

    public TestBehavior(Stat<Integer> damage, Stat<Integer> rof, Persistent<Integer> ammo) {
        this.damage = damage;
        this.rof = rof;
        this.ammo = ammo;
    }

    @Override
    public void handle(Event event) {
        int curDamage = damage.stat();
        int curRof = rof.stat();

        ammo.update(current -> current - 1); //use ammo
    }
}
