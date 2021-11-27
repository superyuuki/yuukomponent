package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.UUID;

public interface BehaviorQuerier {

    CentralisedFuture<List<Behavior>> ordered(List<UUID> uuids);

}
