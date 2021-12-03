package com.superyuuki.yuukomponent.sql;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ArrayFunc implements Function<Object, List<CentralisedFuture<Integer>>> {

    private final List<CentralisedFuture<Integer>> topList;

    public ArrayFunc(List<CentralisedFuture<Integer>> topList) {
        this.topList = topList;
    }

    @Override
    public List<CentralisedFuture<Integer>> apply(Object ignored) {

        List<CentralisedFuture<Integer>> cloneList = new ArrayList<>(topList);

        for (CentralisedFuture<Integer> future : topList) {

        }

        return null;
    }
}
