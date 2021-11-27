package com.superyuuki.yuukomponent.core.component.low;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

public class SetChildOperation implements BiFunction<UUID, List<UUID>, List<UUID>> {

    private final UUID toTarget;
    private final UUID toReplace;

    public SetChildOperation(UUID toTarget, UUID toReplace) {
        this.toTarget = toTarget;
        this.toReplace = toReplace;
    }

    @Override
    public List<UUID> apply(UUID uuid, List<UUID> uuids) {

        List<UUID> toReturn = new ArrayList<>();

        for (UUID uuid1 : uuids) {
            if (uuid1.equals(toTarget)) {
                toReturn.add(toReplace);
            } else {
                toReturn.add(uuid1);
            }
        }

        return toReturn;
    }
}
