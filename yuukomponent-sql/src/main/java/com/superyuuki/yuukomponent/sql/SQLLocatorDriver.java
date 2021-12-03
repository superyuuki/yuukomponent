package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.component.newtype.Component;
import space.arim.jdbcaesar.JdbCaesar;

import java.util.List;
import java.util.UUID;

public class SQLLocatorDriver {

    private final JdbCaesar caesar;

    public SQLLocatorDriver(JdbCaesar caesar) {
        this.caesar = caesar;
    }

    private final static String QUERY_ALL = "" +
            "" +
            "WITH RECURSIVE children AS ( " +
            "SELECT * FROM components " +
            "WHERE parent = ? " +
            "UNION " +
            "SELECT c.* " +
            "FROM components as c, children as ch " +
            "WHERE c.parent = ch.id" +
            ") SELECT * FROM children";

    private final static String QUERY_CHILDREN = "SELECT * FROM components WHERE parent = ?";

    private final static String QUERY_ROOT_ALL = "" +
            "" +
            "WITH RECURSIVE parents AS ( " +
            "SELECT * FROM components " +
            "WHERE parent = ( " +
            "WITH RECURSIVE children AS ( " +
            "SELECT * FROM components " +
            "WHERE id = ? " +
            "UNION " +
            "SELECT c.* " +
            "FROM components as c, children as ch " +
            "WHERE c.id = ch.parent " +
            ") SELECT id FROM components WHERE id = parent " +
            ") " +
            "UNION " +
            "SELECT cv.* " +
            "FROM components as cv, parents as p " +
            "WHERE cv.parent = p.id " +
            ") SELECT * FROM parents";

    private final static String QUERY_ROOT_ID = "" +
            "" +
            "WITH RECURSIVE children AS ( " +
            "SELECT * FROM components " +
            "WHERE id = 4 " +
            "UNION " +
            "SELECT c.* " +
            "FROM components as c, children as ch " +
            "WHERE c.id = ch.parent " +
            ") SELECT * FROM components WHERE parent = " +
            "(SELECT id FROM children WHERE id = parent)";



    public List<Integer> withDescendants(int parent) {
        return caesar
                .query(QUERY_ALL)
                .params(parent)
                .listResult(rs -> rs.getInt("id"))
                .execute();
    }

    public List<Integer> withChildren(int parent) {
        return caesar
                .query(QUERY_CHILDREN)
                .params(parent)
                .listResult(rs -> rs.getInt("id"))
                .execute();
    }

    public List<Integer> fromRoot(int child) {
        return caesar
                .query(QUERY_ROOT_ALL)
                .params(child)
                .listResult(rs -> rs.getInt("id"))
                .execute();
    }

    public List<Integer> fromRootChildren(int child) {
        return caesar
                .query(QUERY_ROOT_ID)
                .params(child)
                .listResult(rs -> rs.getInt("id"))
                .execute();
    }




}
