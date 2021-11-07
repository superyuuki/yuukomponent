package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.blueprint.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.BehaviorLoader;
import com.superyuuki.yuukomponent.api.config.behavior.DataSection;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.config.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

public class SomeBehaviorLoader implements BehaviorLoader {

    @Override
    public BehaviorSource load(DataSection section, EventDispatcher dispatcher) throws WrongTypeValueFailure, NoSuchBehaviorValueFailure {

        int randomVal = section.value("someValue", Integer.class);
        int defaultDamage = section.value("defaultDamage", Integer.class);

        return new SomeBehaviorSource(randomVal, defaultDamage, dispatcher);
    }
}
