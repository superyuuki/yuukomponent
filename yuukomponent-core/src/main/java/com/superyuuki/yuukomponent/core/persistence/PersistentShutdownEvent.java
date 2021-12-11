package com.superyuuki.yuukomponent.core.persistence;

import com.superyuuki.yuukomponent.api.trait.Event;

import java.util.function.Supplier;

/**
 * Called when the persistence system is shutting down
 */
public class PersistentShutdownEvent implements Event {


    public static class Supply implements Supplier<PersistentShutdownEvent> {

        private final PersistentShutdownEvent shutdownEvent = new PersistentShutdownEvent();

        @Override
        public PersistentShutdownEvent get() {
            return shutdownEvent; //share instance
        }
    }
}
