package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.UUID;

public interface CompPool {

    CentralisedFuture<Behavior> get(UUID uuid);
    CentralisedFuture<Boolean> present(UUID uuid);
    CentralisedFuture<Boolean> delete(UUID uuid);

}
