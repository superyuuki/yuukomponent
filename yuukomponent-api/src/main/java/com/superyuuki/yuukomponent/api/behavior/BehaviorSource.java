package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.component.error.NoSuchDefinitionException;

public interface BehaviorSource {


    /**
     * Create a new behavior with default values
     * @return the behavior
     */
    Behavior birth(BehaviorRegistry registry) throws NoSuchDefinitionException;

    /**
     * Create configurable version
     * @param section section
     * @return the section
     */
    Behavior birthWith(ConfSection section, BehaviorRegistry registry) throws NoSuchDefinitionException;

    Behavior birthParenting(ConfSection section, BehaviorRegistry registry, Behavior behavior) throws NoSuchDefinitionException, UnsupportedOperationException;

}
