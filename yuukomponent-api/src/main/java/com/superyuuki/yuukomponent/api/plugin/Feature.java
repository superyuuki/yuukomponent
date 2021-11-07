package com.superyuuki.yuukomponent.api.plugin;

import com.superyuuki.yuukomponent.api.blueprint.BehaviorSource;

import java.util.Collection;
import java.util.Map;

/**
 * Represents a small plugin meant to load some behaviors and a nexus
 */
public interface Feature {

    Map<String, BehaviorSource> behaviors();
    Collection<Archonexus> nexuses();

}
