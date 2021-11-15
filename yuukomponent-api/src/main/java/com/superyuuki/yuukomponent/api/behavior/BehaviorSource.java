package com.superyuuki.yuukomponent.api.behavior;

public interface BehaviorSource {


    /**
     * Create a new behavior of correct type
     * @return the behavior
     */
    Mutator birth();

}
