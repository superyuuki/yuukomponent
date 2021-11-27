package com.superyuuki.yuukomponent.api.component.low;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StructPool extends StructDriver {

    Optional<List<UUID>> getCached(UUID uuid);



}
