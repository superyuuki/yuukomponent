package com.superyuuki.yuukomponent.api.behavior;

import java.util.NoSuchElementException;
import java.util.UUID;

public interface BehaviorRegistry {

    Behavior load(UUID uuid, String behaviorIdentifier) throws NoSuchBehaviorException;

}
