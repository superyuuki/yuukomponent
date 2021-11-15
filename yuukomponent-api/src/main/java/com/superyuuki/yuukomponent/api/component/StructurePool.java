package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.error.MissingRootFailure;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateFailure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * base transactional structure pool
 */
public interface StructurePool {

    //struct ops

    List<UUID> get(Transaction tx, UUID uuid);
    boolean remove(Transaction tx, UUID parent, UUID child) throws MissingRootFailure;

    /**
     *
     * @param tx
     * @param parent
     * @param child
     * @return present if successful, false if already present
     */
    Optional<UUID> add(Transaction tx, UUID parent, UUID child);

    /**
     *
     * @param tx
     * @param parent
     * @param childToReplace
     * @param childToInsert
     * @return true if successful, false if there was no such child present to replace
     */
    Optional<UUID> replace(Transaction tx, UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateFailure;

    // parent ops

    default boolean isDetached(Transaction tx, UUID toCheck) {
        return getParent(tx, toCheck).isEmpty();
    }
    Optional<UUID> getParent(Transaction tx, UUID toCheck);
    UUID[] allWithoutParents(Transaction tx, UUID toCheck); //relatively expensive call

}
