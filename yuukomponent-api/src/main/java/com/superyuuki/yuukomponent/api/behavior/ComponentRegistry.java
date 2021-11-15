package com.superyuuki.yuukomponent.api.behavior;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

/**
 * This is NOT component storage.
 */
public interface ComponentRegistry {

    Component createOfType(String componentIdentifier) throws NoSuchDefinitionException;

}
