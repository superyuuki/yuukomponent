package com.superyuuki.yuukomponent.api.addons.internal;

import com.superyuuki.yuukomponent.api.addons.internal.depends.Node;

import java.util.Collection;

public interface AnnotationProcess {

    Collection<Node> getDependencies();
    String getName();

}
