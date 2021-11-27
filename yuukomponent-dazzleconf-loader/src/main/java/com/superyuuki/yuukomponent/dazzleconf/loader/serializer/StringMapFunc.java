package com.superyuuki.yuukomponent.dazzleconf.loader.serializer;

import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.FlexibleTypeMapEntryFunction;

import java.util.Map;

public class StringMapFunc implements FlexibleTypeMapEntryFunction<String, FlexibleType> {

    public static final StringMapFunc I = new StringMapFunc();

    private StringMapFunc() {}

    @Override
    public Map.Entry<String, FlexibleType> getResult(FlexibleType flexibleType, FlexibleType flexibleType1) throws BadValueException {
        return Map.entry(flexibleType.getString(), flexibleType1);
    }
}
