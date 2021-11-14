package com.superyuuki.yuukomponent.core.component.structure;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.error.MissingRootFailure;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateFailure;
import com.superyuuki.yuukomponent.api.component.structure.StructurePoolFeature;
import com.superyuuki.yuukomponent.api.transactional.Transaction;
import com.superyuuki.yuukomponent.core.component.structure.op.AddChildOperation;
import com.superyuuki.yuukomponent.core.component.structure.op.RemoveChildOperation;
import com.superyuuki.yuukomponent.core.component.structure.op.SetChildOperation;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CachingStructurePoolFeature implements StructurePoolFeature {

    private final StructurePoolFeature delegate;

    private final Cache<UUID, List<UUID>> childrenCache;
    private final Cache<UUID, Optional<UUID>> parentCache;

    public CachingStructurePoolFeature(StructurePoolFeature delegate, Duration cachingExpiry) {
        this.delegate = delegate;

        this.childrenCache = Caffeine.newBuilder().expireAfterAccess(cachingExpiry).build();
        this.parentCache = Caffeine.newBuilder().expireAfterAccess(cachingExpiry).build();
    }

    @Override
    public List<UUID> get(Transaction tx, UUID uuid) {
        return childrenCache.get(uuid, id -> delegate.get(tx, id));
    }

    @Override
    public boolean remove(Transaction tx, UUID parent, UUID child) throws MissingRootFailure {
        boolean result = delegate.remove(tx, parent, child);

        childrenCache.asMap().computeIfPresent(parent, new RemoveChildOperation(child));

        if (result) {
            parentCache.put(child, Optional.empty());
        }

        return result;
    }

    @Override
    public Optional<UUID> add(Transaction tx, UUID parent, UUID child) {

        Optional<UUID> result = delegate.add(tx, parent, child);

        childrenCache.asMap().computeIfPresent(parent, new AddChildOperation(child));

        if (result.isPresent()) {
            parentCache.put(child, result);
        }

        return result;
    }

    @Override
    public Optional<UUID> replace(Transaction tx, UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateFailure {
        Optional<UUID> result = delegate.replace(tx, parent, childToReplace, childToInsert);

        childrenCache.asMap().computeIfPresent(parent, new SetChildOperation(childToReplace, childToInsert));

        if (result.isPresent()) {
            parentCache.put(childToReplace, Optional.empty());
            parentCache.put(childToInsert, result);
        }

        return result;
    }

    @Override
    public Optional<UUID> getParent(Transaction tx, UUID toCheck) {
        return parentCache.get(toCheck, id -> delegate.getParent(tx, id));
    }

    @Override
    public UUID[] allWithoutParents(Transaction tx, UUID toCheck) {
        return delegate.allWithoutParents(tx, toCheck); //no caching
    }
}
