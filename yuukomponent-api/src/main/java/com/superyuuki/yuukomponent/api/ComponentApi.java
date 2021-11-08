package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.blueprint.Blueprint;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.ComponentSource;

import java.util.Optional;
import java.util.UUID;

public interface ComponentApi {

    /**
     * Creates a component
     * @param componentId the id of the component to make
     * @return the component
     */
    Component createFromId(String componentId);

    /**
     * Creates a component from a loader
     * @param loader the loader
     * @return the component
     */
    Component createFromLoader(ComponentSource loader);

    /**
     * Creates a component from a blueprint
     * @param blueprint the blueprint
     * @return the component
     */
    Component createFromBlueprint(Blueprint blueprint);

    /**
     * Create a component from a blueprint id
     * @param blueprintId the id of the blueprint
     * @return the component
     */
    Component createFromBlueprintId(String blueprintId);

    /**
     * Gets a component from storage
     * @param identifier the identifier of the component
     * @return the component if it is active
     */
    Optional<Component> getByUUID(UUID identifier);

    /**
     * Gets a compoent from storage or from the children of any active component in storage
     * @param identifier the identifier of the component
     * @return the component
     */
    Optional<Component> getByUUIDOrChildren(UUID identifier);

}
