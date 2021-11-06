package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Map;

/**
 * This is NOT component storage.
 */
public interface ComponentRegistry {

    ComponentLoader loader(String componentIdentifier) throws NoSuchDefinitionException;

}
