package com.superyuuki.yuukomponent.example;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.behavior.ConfSection;
import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

public class SomeBehaviorLoader implements BehaviorLoader {

    @Override
    public BehaviorSource load(ConfSection section, EventDispatcher dispatcher) throws WrongTypeValueFailure, NoSuchBehaviorValueFailure {

        int randomVal = section.value("someValue", Integer.class);
        int defaultDamage = section.value("defaultDamage", Integer.class);

        return new SomeBehaviorSource(randomVal, defaultDamage, dispatcher);
    }
}
