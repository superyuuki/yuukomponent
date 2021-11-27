package com.superyuuki.yuukomponent.api.component;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.UUID;

public interface ParentQuerier {

    CentralisedFuture<List<UUID>> withoutParents();

}
