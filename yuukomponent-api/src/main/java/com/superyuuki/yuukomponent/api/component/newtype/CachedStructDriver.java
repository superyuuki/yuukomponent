package com.superyuuki.yuukomponent.api.component.newtype;

import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CachedStructDriver extends StructDriver {

    Optional<List<UUID>> getCached(UUID uuid);



}
