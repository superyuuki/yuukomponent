package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.error.MissingRootException;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.newtype.CachedStructDriver;
import com.superyuuki.yuukomponent.api.component.newtype.StructDriver;
import com.superyuuki.yuukomponent.core.component.op.AddChildOperation;
import com.superyuuki.yuukomponent.core.component.op.RemoveChildOperation;
import com.superyuuki.yuukomponent.core.component.op.SetChildOperation;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CaffeineStructDriver implements CachedStructDriver {

    private final StructDriver driver;
    private final Cache<UUID, List<UUID>> childrenCache;
    private final Cache<UUID, Optional<UUID>> parentCache;

    public CaffeineStructDriver(StructDriver driver, Duration cacheTime) {
        this.driver = driver;
        this.childrenCache = Caffeine.newBuilder().expireAfterAccess(cacheTime).build();
        this.parentCache = Caffeine.newBuilder().expireAfterAccess(cacheTime).build();
    }


    @Override
    public List<UUID> get(UUID uuid) throws NoSuchComponentException {
        return childrenCache.get(uuid, driver::get);
    }

    @Override
    public boolean remove(UUID parent, UUID child) throws MissingRootException {
        boolean result = driver.remove(parent, child);

        if (result) {
            childrenCache.asMap().computeIfPresent(parent, new RemoveChildOperation(child));

            parentCache.put(child, Optional.empty());
        }

        return result;
    }

    @Override
    public Optional<UUID> add(UUID parent, UUID child) {
        Optional<UUID> result = driver.add(parent, child);

        if (result.isPresent()) {
            childrenCache.asMap().computeIfPresent(parent, new AddChildOperation(child));

            parentCache.put(child, result);
        }

        return result;
    }

    @Override
    public Optional<UUID> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException {
        Optional<UUID> result = driver.replace(parent, childToReplace, childToInsert);

        if (result.isPresent()) {
            childrenCache.asMap().computeIfPresent(parent, new SetChildOperation(childToReplace, childToInsert));

            parentCache.put(childToReplace, Optional.empty());
            parentCache.put(childToInsert, result);
        }

        return result;
    }

    @Override
    public Optional<UUID> getParent(UUID toCheck) {
        return parentCache.get(toCheck, driver::getParent);
    }

    @Override
    public List<UUID> allWithoutParents(UUID toCheck) {
        return driver.allWithoutParents(toCheck); //fix this lol
    }

    @Override
    public Optional<List<UUID>> getCached(UUID uuid) {
        return Optional.ofNullable(childrenCache.getIfPresent(uuid));
    }
}
