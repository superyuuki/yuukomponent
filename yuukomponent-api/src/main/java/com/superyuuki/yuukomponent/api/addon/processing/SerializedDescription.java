package com.superyuuki.yuukomponent.api.addon.processing;

import com.superyuuki.yuukomponent.api.addon.Feature;
import com.superyuuki.yuukomponent.api.addon.internal.Description;

import java.util.Collection;
import java.util.List;

public class SerializedDescription implements Description {

    private final String displayName;
    private final String version;
    private final String description;
    private final String author;
    private final List<Class<? extends Feature>> exports;
    private final List<Class<? extends Feature>> depends;
    private final String className;

    public SerializedDescription(String displayName, String version, String description, String author, List<Class<? extends Feature>> exports, List<Class<? extends Feature>> depends, String className) {
        this.displayName = displayName;
        this.version = version;
        this.description = description;
        this.author = author;
        this.exports = exports;
        this.depends = depends;
        this.className = className;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public String version() {
        return version;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String author() {
        return author;
    }

    @Override
    public Collection<Class<? extends Feature>> depends() {
        return depends;
    }

    @Override
    public Collection<Class<? extends Feature>> exports() {
        return exports;
    }

    @Override
    public String mainClassName() {
        return className;
    }
}
