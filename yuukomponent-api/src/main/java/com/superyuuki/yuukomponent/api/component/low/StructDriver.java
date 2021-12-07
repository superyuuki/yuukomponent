package com.superyuuki.yuukomponent.api.component.low;

import com.superyuuki.yuukomponent.api.component.error.MissingRootException;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import com.superyuuki.yuukomponent.api.component.newtype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StructDriver {

    List<Component> withDescendants(int parent);
    List<Component> withChildren(int parent);
    List<Component> fromRoot(int child); //query
    List<Component> fromRootChildren(int child);
    List<Component> fromGlobal(); //query every single root component

    boolean remove(UUID parent, UUID child) throws MissingRootException;
    Optional<UUID> add(UUID parent, UUID child);
    Optional<UUID> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException;

}
