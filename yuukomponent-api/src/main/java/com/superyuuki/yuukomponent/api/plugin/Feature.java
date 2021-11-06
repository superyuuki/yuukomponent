package com.superyuuki.yuukomponent.api.plugin;

import com.superyuuki.yuukomponent.api.config.BehaviorLoader;

import java.util.Collection;
import java.util.Map;

/**
 * Represents a small plugin meant to load some behaviors and a nexus
 */
public interface Feature {

    Map<String, BehaviorLoader> behaviors();
    Collection<Archonexus> nexuses();

}
