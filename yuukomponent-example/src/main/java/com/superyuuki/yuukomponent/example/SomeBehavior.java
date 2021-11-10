package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.inbuilt.stat.Stat;
import com.superyuuki.yuukomponent.api.behavior.TypedBehavior;

public class SomeBehavior implements TypedBehavior<SomeBehavior.Event> {

    private int pretendPersistent; //just to simulate loading
    private final Stat<Integer> damage;
    private final Stat<Integer> range;


    public SomeBehavior(int pretendPersistent, Stat<Integer> damage, Stat<Integer> range) {
        this.pretendPersistent = pretendPersistent;
        this.damage = damage;
        this.range = range;
    }

    @Override
    public void handle(Event event) {
        int damageVal = damage.stat();
        int rangeVal = range.stat();

        int eventVal = event.eventValue;

        pretendPersistent = (damageVal * rangeVal + eventVal);

        System.out.println("Final damage: " + pretendPersistent);
    }

    public static class Event implements com.superyuuki.yuukomponent.api.behavior.Event {
        private final int eventValue;

        public Event(int eventValue) {
            this.eventValue = eventValue;
        }

        public int getEventValue() {
            return eventValue;
        }
    }
}
