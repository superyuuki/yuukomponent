package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.ComponentLoadException;

import java.util.UUID;

public interface BehaviorLoader {

    Behavior instantiate(UUID uuid) throws ComponentLoadException;

}
