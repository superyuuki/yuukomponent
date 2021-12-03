package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.newtype.Component;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;
import java.util.UUID;

public interface StructureQuerier {


    CentralisedFuture<List<UUID>> ordered(int id);
    CentralisedFuture<List<UUID>> orderedImmediate(int id);
    CentralisedFuture<List<UUID>> orderedFromRoot(int id);
    CentralisedFuture<List<UUID>> orderedImmediateFromRoot(int id);

    CentralisedFuture<List<UUID>> all();

}
