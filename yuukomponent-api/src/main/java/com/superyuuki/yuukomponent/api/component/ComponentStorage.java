package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

/**
 * Contract representing the storage of all components existent on a platform.
 *
 * Think of it as a storage driver
 */
public interface ComponentStorage {

    Collection<Component> components();

    /**
     * Gets a component by it's unique identifier.
     * @param uuid the identifier
     * @return the component if it is present
     */
    Optional<Component> retrieveOpt(UUID uuid);

    /**
     * Gets a component by it's unique identifier
     * @param uuid the identifier
     * @throws NoSuchElementException if there is no component with that id
     * @return the component
     */
    Component retrieve(UUID uuid) throws NoSuchElementException;

    /**
     * Gets whether component already exists
     * @param uuid the id
     * @return whether it exists
     */
    boolean present(UUID uuid);

}
