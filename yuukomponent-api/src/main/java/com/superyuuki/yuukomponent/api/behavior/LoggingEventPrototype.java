package com.superyuuki.yuukomponent.api.behavior;

/**
 * TODO Implement an event wrapper that if any behavior uses it it is locked
 * labels: low-priority
 * The necessity of this is for caching: If a component's behaviors are still being used by incoming events,
 * there is no need to recycle it. However, if the node cache hasn't had any events sent to it that actually
 * did anything, why keep the node in the cache?
 */
public interface LoggingEventPrototype {
}
