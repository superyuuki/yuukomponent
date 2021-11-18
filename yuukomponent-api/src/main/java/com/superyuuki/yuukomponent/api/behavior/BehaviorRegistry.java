package com.superyuuki.yuukomponent.api.behavior;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

/**
 * The source of every behavior in existence.
 */
public interface BehaviorRegistry {

    Behavior createOfType(String componentIdentifier) throws NoSuchDefinitionException;

}
