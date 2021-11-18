package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.behavior.Behavior;

import java.util.Optional;
import java.util.UUID;

public interface CachedCompDriver extends CompDriver {

    Optional<Behavior> getCached(UUID uuid);

}
