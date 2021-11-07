package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.NoSuchDefinitionException;
import com.superyuuki.yuukomponent.api.component.ComponentSource;

/**
 * This is NOT component storage.
 */
public interface ComponentRegistry {

    ComponentSource loader(String componentIdentifier) throws NoSuchDefinitionException;

}
