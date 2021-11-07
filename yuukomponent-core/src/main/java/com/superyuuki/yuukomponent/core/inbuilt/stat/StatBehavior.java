package com.superyuuki.yuukomponent.core.inbuilt.stat;

import com.superyuuki.yuukomponent.api.behavior.Behavior;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatCalculationEvent;
import com.superyuuki.yuukomponent.core.behavior.TypedBehavior;

public class StatBehavior implements TypedBehavior<StatCalculationEvent<?>> {
    @Override
    public void handle(StatCalculationEvent<?> event) {
        event.modify();
    }
}
