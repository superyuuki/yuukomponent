package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.ConfSection;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

import java.util.Map;

public class ConfSectionSerializer implements ValueSerialiser<ConfSection> {


    @Override
    public Class<ConfSection> getTargetClass() {
        return ConfSection.class;
    }

    @Override
    public ConfSection deserialise(FlexibleType flexibleType) throws BadValueException {

        flexibleType.getObject()

        Map<String, FlexibleType> map = flexibleType.getMap((key, value) -> Map.entry(key.getString(), value));



        return null;
    }

    @Override
    public Object serialise(ConfSection confSection, Decomposer decomposer) {
        decomposer.decompose()

        return null;
    }
}
