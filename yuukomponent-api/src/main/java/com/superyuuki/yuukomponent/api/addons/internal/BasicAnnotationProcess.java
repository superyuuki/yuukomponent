package com.superyuuki.yuukomponent.api.addons.internal;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.AddonContext;
import com.superyuuki.yuukomponent.api.addons.internal.depends.Node;

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
            nodes.add(new Node(name, clazz));
        }

        return nodes;
    }

    @Override
    public String getName() {
        return context.name();
    }

}
