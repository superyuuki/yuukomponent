package com.superyuuki.yuukomponent.sql.newtype.impl;

import com.superyuuki.yuukomponent.api.component.error.NoSuchComponentException;
import com.superyuuki.yuukomponent.api.component.storage.Children;
import com.superyuuki.yuukomponent.api.component.storage.Tree;
import com.superyuuki.yuukomponent.sql.newtype.contract.LocalRecursor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocalRecursorImpl implements LocalRecursor {

    private final Map<Integer, Children> map;
    private final int initialKey;

    public LocalRecursorImpl(Map<Integer, Children> map, int initialKey) {
        this.map = map;
        this.initialKey = initialKey;
    }

    @Override
    public Tree recursive() {

        List<Integer> collect = new ArrayList<>();

        recurse(collect, initialKey);

        return new Tree(collect.toArray(new Integer[0]));
    }

    void recurse(List<Integer> builtList, Integer select) {
        builtList.add(select);

        Children nb = map.get(select);
        if (nb == null) throw new NoSuchComponentException(select);

        for (Integer i : nb.directDescendants()) {
            recurse(builtList, i);
        }
    }
}
