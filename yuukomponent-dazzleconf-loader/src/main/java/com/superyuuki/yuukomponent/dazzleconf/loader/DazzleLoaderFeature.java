package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.behavior.ComponentRegistry;
import com.superyuuki.yuukomponent.api.behavior.RegistryEntrypoint;
import com.superyuuki.yuukomponent.api.behavior.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DazzleLoaderFeature implements RegistryEntrypoint {

    private final Path[] files;
    private final UUIDProvider provider;
    private final BehaviorRegistry behaviorRegistry;
    private final EventDispatcher dispatcher;

    public DazzleLoaderFeature(Path[] files, UUIDProvider provider, BehaviorRegistry behaviorRegistry, EventDispatcher dispatcher) {
        this.files = files;
        this.provider = provider;
        this.behaviorRegistry = behaviorRegistry;
        this.dispatcher = dispatcher;
    }


    /**
     * Loads the configuration using dazzleconf
     *
     * @return registry
     * @throws BadConfigFailure if config bad
     * @throws IOException if exception
     */
    @Override
    public ComponentRegistry load() throws BadConfigFailure, IOException {
        return new MappedComponentRegistry(
                Map.copyOf(
                        new SourceLoader(
                                new SectionLoader(files).load(),
                                behaviorRegistry,
                                dispatcher,
                                provider
                        ).load()
                )
        );
    }

}
