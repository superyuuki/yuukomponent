package com.superyuuki.yuukomponent.core.component;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.superyuuki.yuukomponent.api.component.error.MissingRootUpdateException;
import com.superyuuki.yuukomponent.api.component.newtype.StructDriver;
import com.superyuuki.yuukomponent.api.component.newtype.StructPool;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class CaffeineStructPool implements StructPool {

    private final StructDriver driver;
    private final FactoryOfTheFuture factory;
    private final AsyncLoadingCache<UUID, List<UUID>> childrenCache;
    private final AsyncLoadingCache<UUID, Optional<UUID>> parentCache;

    public CaffeineStructPool(StructDriver driver, FactoryOfTheFuture factory, Duration duration) {
        this.driver = driver;
        this.factory = factory;
        this.childrenCache = Caffeine.newBuilder().expireAfterAccess(duration).buildAsync((uuid, executor) -> factory.supplyAsync(() -> driver.get(uuid)));
        this.parentCache = Caffeine.newBuilder().expireAfterAccess(duration).buildAsync((uuid, executor) -> factory.supplyAsync(() -> driver.getParent(uuid)));
    }

    @Override
    public CentralisedFuture<List<UUID>> get(UUID uuid) {
        return (CentralisedFuture<List<UUID>>) childrenCache.get(uuid);
    }

    @Override
    public CentralisedFuture<Boolean> remove(UUID parent, UUID child) {
      return factory.supplyAsync(() -> driver.remove(parent, child)).thenCompose(bool -> {

          if (bool) {
              return childrenCache.synchronous().refresh(parent).thenApply(ignored -> true);
          }

          return factory.completedFuture(false);

      });
    }

    @Override
    public CentralisedFuture<Optional<UUID>> add(UUID parent, UUID child) {
        return factory.supplyAsync(() -> driver.add(parent, child)).thenCompose(opt -> {

            if (opt.isPresent()) {
                return childrenCache.synchronous().refresh(parent).thenApply(ignored -> opt);
            }

            return factory.completedFuture(opt);

        });
    }

    @Override
    public CentralisedFuture<Optional<UUID>> replace(UUID parent, UUID childToReplace, UUID childToInsert) throws MissingRootUpdateException {

        return factory.supplyAsync(() -> driver.replace(parent, childToReplace, childToInsert)).thenCompose(opt -> {

            if (opt.isPresent()) {
                return childrenCache.synchronous().refresh(parent).thenApply(ignored -> opt);
            }

            return factory.completedFuture(opt);

        });

    }

    @Override
    public CentralisedFuture<Boolean> isDetached(UUID toCheck) {
        return factory.supplyAsync(() -> driver.isDetached(toCheck));
    }

    @Override
    public CentralisedFuture<Optional<UUID>> getParent(UUID toCheck) {
        return (CentralisedFuture<Optional<UUID>>) parentCache.get(toCheck);
    }

    @Override
    public CentralisedFuture<List<UUID>> allWithoutParents(UUID toCheck) {
        throw new UnsupportedOperationException("not yet");
    }
}
