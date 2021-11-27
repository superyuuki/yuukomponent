package com.superyuuki.yuukomponent.api.component;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.UUID;

public interface StructureQuerier {

    /**
     * Returns a complete path of self and all child components in execution order
     * @param uuid the uuid to use
     * @return lol
     */
    CentralisedFuture<List<UUID>> ordered(UUID uuid);
    CentralisedFuture<List<UUID>> orderedImmediate(UUID uuid);

}
