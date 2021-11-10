package com.superyuuki.yuukomponent.api.config.behavior;

import java.util.Optional;

public interface BehaviorRegistry {

    Optional<BehaviorLoader> loader(String behaviorIdentifier);

}
