package com.superyuuki.yuukomponent.core.addon;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.superyuuki.yuukomponent.api.addon.Feature;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.api.addon.internal.Description;
import com.superyuuki.yuukomponent.core.addon.error.DuplicateExportFailure;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Credit to velocity
 *
 * Taken mostly from PluginDependencyUtil
 */
public class AddonSorter {

    private final List<AddonLoader> loaders;

    public AddonSorter(List<AddonLoader> loaders) {
        this.loaders = loaders;
    }

    public List<AddonLoader> sort() throws StartupFailure {

        MutableGraph<AddonLoader> graph = GraphBuilder.directed()
                .allowsSelfLoops(false)
                .expectedNodeCount(loaders.size())
                .build();

        Map<Class<? extends Feature>, AddonLoader> providers = new HashMap<>();

        for (AddonLoader loader : loaders) {
            for (Class<? extends Feature> feature : loader.description().exports()) {
                Description oldDesk = providers.get(feature).description();

                if (oldDesk != null) {
                    throw new DuplicateExportFailure(oldDesk.displayName(), loader.description().displayName(), feature.getCanonicalName());
                }

                providers.put(feature, loader);
            }
        }

        for (AddonLoader dependentAddon : loaders) {
            graph.addNode(dependentAddon);

            for (Class<? extends Feature> dependency : dependentAddon.description().depends()) {
                AddonLoader providingAddon = providers.get(dependency);

                if (providingAddon != null) {
                    graph.putEdge(dependentAddon, providingAddon);
                }
            }
        }

        //finished populating graph now sort

        List<AddonLoader> loaders1 = new ArrayList<>();
        Map<AddonLoader, Mark> marks = new HashMap<>();

        for (AddonLoader node : graph.nodes()) {
            visitNode(graph, node, marks, loaders1, new ArrayDeque<>());
        }

        return loaders1;

    }

    private void visitNode(Graph<AddonLoader> dependencyGraph, AddonLoader current, Map<AddonLoader, Mark> visited, List<AddonLoader> sorted, Deque<AddonLoader> currentDependencyScanStack) {
        Mark mark = visited.getOrDefault(current, Mark.NOT_VISITED);
        if (mark == Mark.VISITED) {
            return;
        } else if (mark == Mark.VISITING) {
            currentDependencyScanStack.addLast(current);
            final String loop = currentDependencyScanStack.stream().map(loader -> loader.description().displayName()).collect(Collectors.joining(" -> "));
            throw new IllegalStateException("Circular dependency detected: " + loop);
        }

        currentDependencyScanStack.addLast(current);
        visited.put(current, Mark.VISITING);
        for (AddonLoader edge : dependencyGraph.successors(current)) {
            visitNode(dependencyGraph, edge, visited, sorted, currentDependencyScanStack);
        }

        visited.put(current, Mark.VISITED);
        currentDependencyScanStack.removeLast();
        sorted.add(current);
    }

    private enum Mark {
        NOT_VISITED,
        VISITING,
        VISITED
    }

}
