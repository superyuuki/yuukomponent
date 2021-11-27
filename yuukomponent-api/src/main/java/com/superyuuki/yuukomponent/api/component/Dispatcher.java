package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.List;

public interface Dispatcher {

    void receive(CentralisedFuture<List<Behavior>> futures);

}
