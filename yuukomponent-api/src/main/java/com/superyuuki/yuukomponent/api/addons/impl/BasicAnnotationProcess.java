package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasicAnnotationProcess implements AnnotationProcess {

    //TODO scalar load
    private final AddonContext context;

    public BasicAnnotationProcess(AddonContext context) {
        this.context = context;
    }

    @Override
    public Collection<Node> getDependencies() {

        List<Node> nodes = new ArrayList<>();

        for (Class<? extends Addon> clazz : context.dependencies()) {
            nodes.add(new Node(context.name(), clazz));
        }

        return nodes;
    }

    @Override
    public String getName() {
        return context.name();
    }

}
