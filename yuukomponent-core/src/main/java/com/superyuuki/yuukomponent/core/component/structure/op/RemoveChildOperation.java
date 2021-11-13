package com.superyuuki.yuukomponent.core.component.structure.op;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class RemoveChildOperation implements BiFunction<UUID, List<UUID>, List<UUID>> {

    private final UUID toRemove;

    public RemoveChildOperation(UUID toRemove) {
        this.toRemove = toRemove;
    }

    @Override
    public List<UUID> apply(UUID uuid, List<UUID> uuids) {

        List<UUID> newList = new ArrayList<>();

        for (UUID uuid1 : uuids) {
            if (!uuid1.equals(toRemove)) {
                newList.add(uuid1);
            }
        }

        return newList;
    }
}
