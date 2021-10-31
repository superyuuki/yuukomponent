package com.superyuuki.yuukomponent.core.centralized;

import com.superyuuki.yuukomponent.api.Behavior;

import java.util.UUID;

public interface BehaviorRegistry {

    Behavior load(UUID uuid, String behaviorIdentifier);

}
