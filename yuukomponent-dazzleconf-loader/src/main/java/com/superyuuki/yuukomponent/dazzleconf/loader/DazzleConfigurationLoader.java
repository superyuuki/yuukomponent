package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.ComponentRegistry;
import com.superyuuki.yuukomponent.api.config.ConfigurationLoader;
import com.superyuuki.yuukomponent.api.config.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;
import com.superyuuki.yuukomponent.core.config.MappedComponentRegistry;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DazzleConfigurationLoader implements ConfigurationLoader {

    private final Path[] files;
    private final UUIDProvider provider;
    private final BehaviorRegistry behaviorRegistry;
    private final EventDispatcher dispatcher;

    public DazzleConfigurationLoader(Path[] files, UUIDProvider provider, BehaviorRegistry behaviorRegistry, EventDispatcher dispatcher) {
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
