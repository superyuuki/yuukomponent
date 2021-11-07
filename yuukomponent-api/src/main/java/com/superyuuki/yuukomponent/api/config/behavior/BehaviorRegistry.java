package com.superyuuki.yuukomponent.api.config.behavior;

import com.superyuuki.yuukomponent.api.blueprint.BehaviorSource;

import java.util.Optional;

public interface BehaviorRegistry {

    Optional<BehaviorSource> loader(String behaviorIdentifier);

}
