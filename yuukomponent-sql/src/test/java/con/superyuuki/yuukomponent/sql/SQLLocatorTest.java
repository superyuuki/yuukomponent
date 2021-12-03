package con.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;
import space.arim.omnibus.util.concurrent.impl.IndifferentFactoryOfTheFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SQLLocatorTest {

    private final Logger logger = LoggerFactory.getLogger(SQLLocatorTest.class);

    @Test
    public void testLoadRec() throws InterruptedException {
        FactoryOfTheFuture factory = new IndifferentFactoryOfTheFuture();

        AsyncLoadingCache<Integer, List<Integer>> testCache = Caffeine.newBuilder().buildAsync(new MockCacheLoader());

        testCache.getAll(List.of(1)).thenCompose(ignored -> {
            return recursive(1, testCache, factory);
        }).thenAccept(n -> {
            logger.info(() -> String.valueOf(n));
        }).exceptionally(thr -> {
            logger.error(thr, () -> "??");
            return null;
        });


        /*.thenRun(() -> {

            logger.info(() -> "running");

            Objects.requireNonNull(testCache.get(2)).thenAccept(li -> {
                logger.info(() -> String.valueOf(li));
            });

            logger.info(() -> "end");


        })*/


    }

    public CompletableFuture<List<Integer>> recursive(int top, AsyncLoadingCache<Integer, List<Integer>> cache, FactoryOfTheFuture factory) {
        return cache.get(top).thenCompose(li -> {

            List<CompletableFuture<List<Integer>>> col = new ArrayList<>();

            for (Integer held : li) {
                col.add(recursive(held, cache, factory));
            }

            return CompletableFuture.allOf(col.toArray(new CompletableFuture[]{})).thenApply(ignored -> {
                List<Integer> toReturn = new ArrayList<>();
                toReturn.add(top);

                for (CompletableFuture<List<Integer>> subfuture : col) {
                    toReturn.addAll(subfuture.join());
                }

                return toReturn;
            });

        });
    }

}
