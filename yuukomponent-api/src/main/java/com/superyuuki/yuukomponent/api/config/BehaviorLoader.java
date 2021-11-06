package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.ComponentLoadException;
import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.config.data.DataSection;

import java.util.UUID;

public interface BehaviorLoader {

    Behavior instantiate(UUID id, DataSection dataSection) throws ComponentLoadException;

}
