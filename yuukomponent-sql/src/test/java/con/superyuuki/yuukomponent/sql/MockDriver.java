package con.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.sql.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockDriver implements Driver {

    @Override
    public List<Integer> immediateChildren(Integer id) {
        throw new IllegalStateException("nope");
    }

    @Override
    public Map<Integer, List<Integer>> allChildren(Integer id) {
        return Map.of(
                1, List.of(2,3),
                2, List.of(4,5),
                3, List.of(6,7),
                4, List.of(8),
                5, List.of(),
                6, List.of(9),
                7, List.of(),
                8, List.of(),
                9, List.of()
        );
    }
}
