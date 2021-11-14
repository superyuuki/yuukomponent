package com.superyuuki.yuukomponent.api.behavior;

/**
 * Event that will be dispatched instantaneously to every single component at the same time, not waiting for normal tree order
 * Make sure you know what you're doing
 */
public interface ParallelEvent extends Event {
}
