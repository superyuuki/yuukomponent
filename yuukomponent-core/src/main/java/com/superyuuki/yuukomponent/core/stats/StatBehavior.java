package com.superyuuki.yuukomponent.core.stats;

import com.superyuuki.yuukomponent.api.behavior.types.AsyncBehavior;

import java.util.Map;
import java.util.function.UnaryOperator;

public class StatBehavior implements AsyncBehavior<StatCalculationEvent<?>> {

    private final Map<String, UnaryOperator<?>> operatorMap;

    public StatBehavior(Map<String, UnaryOperator<?>> operatorMap) {
        this.operatorMap = operatorMap;
    }

    @Override
    public void handle(StatCalculationEvent<?> event) {
        //TODO Reimplement stat calculations with type checking, etc etc
    }
}
