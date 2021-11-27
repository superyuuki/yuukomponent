package com.superyuuki.yuukomponent.api.component.low;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.component.low.CompDriver;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompPool extends CompDriver {

    Optional<Behavior> getCached(UUID uuid);

    List<Behavior> getCached(List<UUID> uuids);

}
