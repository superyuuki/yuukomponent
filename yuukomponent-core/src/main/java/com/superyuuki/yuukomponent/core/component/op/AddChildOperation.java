package com.superyuuki.yuukomponent.core.component.op;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class AddChildOperation implements BiFunction<UUID, List<UUID>, List<UUID>> {

    private final UUID toAdd;

    public AddChildOperation(UUID toAdd) {
        this.toAdd = toAdd;
    }


    @Override
    public List<UUID> apply(UUID uuid, List<UUID> uuids) {

        List<UUID> newList = new ArrayList<>(uuids);

        if (!newList.contains(toAdd)) newList.add(toAdd);

        return newList;
    }

}
