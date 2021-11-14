package com.superyuuki.yuukomponent.api.config;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.config.error.NoSuchDefinitionException;

/**
 * This is NOT component storage.
 */
public interface ComponentRegistry {

    Component createOfType(String componentIdentifier) throws NoSuchDefinitionException;

}
