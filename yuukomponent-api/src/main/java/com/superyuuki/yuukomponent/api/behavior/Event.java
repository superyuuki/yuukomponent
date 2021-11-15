package com.superyuuki.yuukomponent.api.behavior;

/**
 * Events should not contain any state, even threadsafe atomic sexy state. This is by design because of the semantics of
 * mutators choosing whether they want to be parallel or synchronous.
 *
 * This is explicitly violated by the official stats addon, because no one else should be using that event.
 */
public interface Event {
}
