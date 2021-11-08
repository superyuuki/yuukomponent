package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.config.error.NoSuchDefinitionFailure;
import com.superyuuki.yuukomponent.api.component.ComponentSource;

/**
 * This is NOT component storage.
 */
public interface ComponentRegistry {

    ComponentSource loader(String componentIdentifier) throws NoSuchDefinitionFailure;

}
