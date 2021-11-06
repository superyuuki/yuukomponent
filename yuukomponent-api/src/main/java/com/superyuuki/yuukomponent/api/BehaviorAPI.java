package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.config.BehaviorLoader;

public interface BehaviorAPI {

    /**
     * Registers a behavior loader
     * @param loader the behavior laoder
     * @param behaviorId the id to assign to the behavior loader
     * @return true if successful, false if something already exists with that id
     */
    boolean registerBehavior(String behaviorId, BehaviorLoader loader);

    /**
     * Checks whether a behavior exists
     * @param behaviorId the identifier
     * @return true if it does, false if it does not
     */
    boolean existsBehavior(String behaviorId);

}
