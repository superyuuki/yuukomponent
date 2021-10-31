package com.superyuuki.yuukomponent.core.behavior;

import com.superyuuki.yuukomponent.api.keyed.stat.Stat;

public class SomeBehavior implements TypedBehavior<SomeBehavior.Event> {

    private final Stat<Integer> stat;

    public SomeBehavior(Stat<Integer> stat) {
        this.stat = stat;
    }

    @Override
    public void handle(Event event) {
        int someValue = stat.stat();

        System.out.println(someValue + event.eventValue);
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
