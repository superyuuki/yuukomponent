package com.superyuuki.yuukomponent.sql;

import java.util.Collection;
import java.util.Map;

public interface Driver {

    /**
     * Complex method that, for every key provided, loads that key's full dependency tree.
     * For every object in the tree, a mapping is provided from parent to child.
     *
     * For instance, if a query is made with root keys 1 and 2, and we have two structures like so:
     *
     *    1        2
     *   3 4      7
     *  5 6      8 9 10
     *
     *  a map is returned with the values
     *  1 -> 3,4
     *  3 -> 5,6
     *  4 -> x
     *  5 -> x
     *  6 -> x
     *  2 -> 7
     *  7 -> 8,9,10
     *  ..etc
     *
     * @param keys a collection of keys representing presumably root node or significant parent nodes
     * @return a map of integers to children guaranteed to hold a mapping for every requested key as well as
     * recursive mappings for each of the root keys' descendants
     */
    Map<Integer, Integer[]> loadTrees(Collection<Integer> keys);

    Map<Integer, String> loadTypes(Collection<Integer> keys);

    /**
     * Fallback method for when a parent is invalidated during a recursion call
     * @param key the key
     * @return children
     */
    Integer[] loadChildren(Integer key);



}
