package con.superyuuki.yuukomponent.sql;

import com.github.benmanes.caffeine.cache.AsyncCacheLoader;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class MockCacheLoader implements AsyncCacheLoader<Integer, List<Integer>> {
    @Override
    public CompletableFuture<? extends List<Integer>> asyncLoad(Integer key, Executor executor) throws Exception {
        throw new IllegalStateException("NO: Key loaded: " + key); //TODO impl
    }

    @Override
    public CompletableFuture<? extends Map<? extends Integer, ? extends List<Integer>>> asyncLoadAll(Set<? extends Integer> keys, Executor executor) throws Exception {
        return CompletableFuture.supplyAsync(() -> Map.of(
                1, List.of(2,3),
                2, List.of(4,5),
                3, List.of(6,7),
                4, List.of(8),
                5, List.of(),
                6, List.of(9),
                7, List.of(),
                8, List.of(),
                9, List.of()
        ));
    }
}
