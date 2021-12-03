package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.component.newtype.Component;

import java.util.List;
import java.util.Map;

public interface Driver {

    List<Integer> immediateChildren(Integer id);
    Map<Integer, List<Integer>> allChildren(Integer id);

}
