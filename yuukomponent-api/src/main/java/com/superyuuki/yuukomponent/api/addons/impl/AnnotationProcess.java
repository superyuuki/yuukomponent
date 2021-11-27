package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.impl.Node;

import java.util.Collection;

public interface AnnotationProcess {

    Collection<Node> getDependencies();
    String getName();

}
