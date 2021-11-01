package com.superyuuki.yuukomponent.core.behavior.behavior;

import com.superyuuki.yuukomponent.api.behavior.Event;
import com.superyuuki.yuukomponent.api.behavior.Behavior;

public interface TypedBehavior<T extends Event> {

    void handle(T event);

    class Adapter<T extends Event> implements Behavior {

        private final TypedBehavior<T> behavior;
        private final Class<T> clazz;

        public Adapter(TypedBehavior<T> behavior, Class<T> clazz) {
            this.behavior = behavior;
            this.clazz = clazz;
        }

        @Override
        public void handle(Event event) {
            if (clazz.isInstance(event)) {
                behavior.handle(clazz.cast(event));
            }
        }
    }

}
