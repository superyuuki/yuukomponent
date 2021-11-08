package com.superyuuki.yuukomponent.stat;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.BehaviorLoader;
import com.superyuuki.yuukomponent.api.config.behavior.DataSection;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

public class StatBehaviorLoader implements BehaviorLoader {

    @Override
    public BehaviorSource load(DataSection section, EventDispatcher dispatcher) throws WrongTypeValueFailure, NoSuchBehaviorValueFailure {



        return null;
    }
}
