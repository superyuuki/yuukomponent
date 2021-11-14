package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.superyuuki.yuukomponent.api.component.Component;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.config.error.NoSuchDefinitionException;
import org.checkerframework.checker.nullness.qual.PolyNull;

import java.util.UUID;
import java.util.function.Function;

public interface ThrowingCache extends Cache<UUID, Component> {

    @Override
    @PolyNull Component get(UUID uuid, Function<? super UUID, ? extends @PolyNull Component> function) throws NoSuchComponentException, NoSuchDefinitionException;
}
