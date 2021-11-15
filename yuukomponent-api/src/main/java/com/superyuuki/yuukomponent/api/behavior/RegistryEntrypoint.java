package com.superyuuki.yuukomponent.api.behavior;


import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.behavior.error.BadConfigFailure;

public interface RegistryEntrypoint extends Addon {

    ComponentRegistry load(BehaviorRegistry behaviorRegistry) throws BadConfigFailure;

}
