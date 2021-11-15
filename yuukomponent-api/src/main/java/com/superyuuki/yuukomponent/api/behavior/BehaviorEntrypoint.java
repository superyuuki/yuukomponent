package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.addons.Addon;

public interface BehaviorEntrypoint extends Addon {

    BehaviorReader[] generateSources();

}
