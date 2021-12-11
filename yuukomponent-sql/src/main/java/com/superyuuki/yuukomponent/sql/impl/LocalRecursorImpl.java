package com.superyuuki.yuukomponent.sql.impl;

import com.superyuuki.yuukomponent.api.component.storage.error.NoSuchComponentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalRecursorImpl implements LocalRecursor {

    private final Map<Integer, Integer[]> map;
    private final int initialKey;

    public LocalRecursorImpl(Map<Integer, Integer[]> map, int initialKey) {
        this.map = map;
        this.initialKey = initialKey;
    }

    @Override
    public Integer[] recursive() {

        List<Integer> collect = new ArrayList<>();

        recurse(collect, initialKey);

        return collect.toArray(Integer[]::new);
    }

    void recurse(List<Integer> builtList, Integer select) {
        builtList.add(select);

        Integer[] nb = map.get(select);
        if (nb == null) throw new NoSuchComponentException(select);

        for (Integer i : nb) {
            recurse(builtList, i);
        }
    }
}
