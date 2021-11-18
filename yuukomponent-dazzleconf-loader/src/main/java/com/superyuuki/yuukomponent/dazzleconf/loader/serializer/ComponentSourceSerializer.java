package com.superyuuki.yuukomponent.dazzleconf.loader.serializer;

import com.superyuuki.yuukomponent.api.behavior.BehaviorSource;
import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;
import com.superyuuki.yuukomponent.dazzleconf.loader.ComponentSource;
import com.superyuuki.yuukomponent.dazzleconf.loader.DazzleConfSection;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.serialiser.Decomposer;
import space.arim.dazzleconf.serialiser.FlexibleType;
import space.arim.dazzleconf.serialiser.ValueSerialiser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Daddy a248 makes it so that the fucking serializers can only throw badvalueexceptions and not anything more descriptive
 * he's too inactive or simply ignores me to the point where he does not actually reply to on in discord or github issues
 * and so i'm forced to make do with shitty badvalueexception catch rethrow catch rethrow instead of firing out descriptive exceptions.
 * Probably imperative to either fork dazzleconf or write my own configuration library so i don't have to deal with this bullshit.
 * Maybe convince yegor256's group to make a EO config library?
 */
public class ComponentSourceSerializer implements ValueSerialiser<ComponentSource> {

    private final Map<String, BehaviorReader> readerMap;

    public ComponentSourceSerializer(Map<String, BehaviorReader> readerMap) {
        this.readerMap = readerMap;
    }

    @Override
    public Class<ComponentSource> getTargetClass() {
        return ComponentSource.class;
    }

    @Override
    public ComponentSource deserialise(FlexibleType flexibleType) throws BadValueException {
        List<FlexibleType> list = flexibleType.getList();

        List<BehaviorSource> sources = new ArrayList<>();

        for (FlexibleType type : list) {
            //deserialization time!!!!!!

            String componentId = flexibleType.getAssociatedKey();

            Map<String, FlexibleType> map = type.getMap(StringMapFunc.I);

            FlexibleType behaviorType = map.get("type");

            if (behaviorType == null) throw new BadValueException.Builder().key("type:missing").build();
            String identifier = behaviorType.getString();

            BehaviorReader reader = readerMap.get(identifier);

            if (reader == null) throw new BadValueException.Builder().key("type:bad").message(componentId).build();

            try {
                sources.add(reader.source(new DazzleConfSection(componentId, map)));
            } catch (NoSuchBehaviorValueFailure e) {
                throw new BadValueException.Builder().key("value:missing").cause(e).build();
            } catch (WrongTypeValueFailure e) {
                throw new BadValueException.Builder().key("value:fucked").cause(e).build();
            }

        }
    }

    @Override
    public Object serialise(ComponentSource componentSource, Decomposer decomposer) {
        return null;
    }
}
