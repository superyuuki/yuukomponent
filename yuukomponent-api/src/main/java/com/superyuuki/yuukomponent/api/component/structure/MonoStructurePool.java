package com.superyuuki.yuukomponent.api.component.structure;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.Optional;
import java.util.UUID;

/**
 * Structure pool that performs a single operation per transaction. Not recommended.
 */
public interface MonoStructurePool {


    CentralisedFuture<UUID[]> get(UUID uuid);
    CentralisedFuture<Boolean> remove(UUID parent, UUID child);
    CentralisedFuture<Boolean> add(UUID parent, UUID child);
    CentralisedFuture<Boolean> replace(UUID parent, UUID toReplace, UUID newComponent);
    CentralisedFuture<Boolean> isDetached(UUID toCheck);
    CentralisedFuture<Optional<UUID>> getParent(UUID toGet);
    CentralisedFuture<UUID[]> withoutParents();


}
