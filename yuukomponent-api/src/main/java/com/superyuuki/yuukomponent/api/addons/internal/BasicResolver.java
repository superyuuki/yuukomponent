package com.superyuuki.yuukomponent.api.addons.internal;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.internal.depends.Graph;
import com.superyuuki.yuukomponent.api.addons.internal.depends.Node;
import com.superyuuki.yuukomponent.api.addons.error.AddonValidityFailure;

import java.util.Collection;
import java.util.List;

public class BasicResolver implements Resolver {

    private final Collection<Class<? extends Addon>> addonClasses;
    private final Validator validator;

    public BasicResolver(Collection<Class<? extends Addon>> addonClasses, Validator validator) {
        this.addonClasses = addonClasses;
        this.validator = validator;
    }

    @Override
    public List<Node> resolve() throws AddonValidityFailure {
        final Graph graph = new Graph();
        for (Class<? extends Addon> pluginClass : addonClasses) {

            AnnotationProcess process = new BasicAnnotationProcess(validator.validate(pluginClass));
            final Collection<Node> deps = process.getDependencies();
            final Node node = new Node(process.getName(), pluginClass);
            if (deps.isEmpty()) {
                graph.addLeaf(node);
            } else {
                graph.addEdges(node, deps);
            }
        }

        return graph.resolve();
    }
}
