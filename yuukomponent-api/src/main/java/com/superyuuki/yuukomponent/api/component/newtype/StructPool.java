package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StructPool {

    CentralisedFuture<List<UUID>> get(UUID uuid);
    CentralisedFuture<Boolean> remove(UUID parent, UUID child);
    CentralisedFuture<Optional<UUID>> add(UUID parent, UUID child);
    CentralisedFuture<Optional<UUID>> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException;
    CentralisedFuture<Boolean> isDetached(UUID toCheck);
    CentralisedFuture<Optional<UUID>> getParent(UUID toCheck);
    CentralisedFuture<List<UUID>> allWithoutParents(UUID toCheck);

}
