package com.superyuuki.yuukomponent.api.component.low;

import com.superyuuki.yuukomponent.api.component.error.MissingRootException;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StructDriver {

    List<UUID> get(UUID uuid) throws NoSuchComponentException;
    boolean remove(UUID parent, UUID child) throws MissingRootException;
    Optional<UUID> add(UUID parent, UUID child);
    Optional<UUID> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException;
    default boolean isDetached(UUID toCheck) {
        return getParent(toCheck).isEmpty();
    }
    Optional<UUID> getParent(UUID toCheck);
    List<UUID> allWithoutParents(); //relatively expensive call

}
