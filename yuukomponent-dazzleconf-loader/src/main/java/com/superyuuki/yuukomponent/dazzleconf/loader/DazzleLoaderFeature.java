package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.trait.TraitFactory;
import com.superyuuki.yuukomponent.api.trait.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.event.EventDispatcher;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class DazzleLoaderFeature implements RegistryEntrypoint {

    private final Path[] files;
    private final UUIDProvider provider;
    private final TraitFactory traitFactory;
    private final EventDispatcher dispatcher;

    public DazzleLoaderFeature(Path[] files, UUIDProvider provider, TraitFactory traitFactory, EventDispatcher dispatcher) {
        this.files = files;
        this.provider = provider;
        this.traitFactory = traitFactory;
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
    public TraitFactory load() throws BadConfigFailure, IOException {
        return new MappedComponentRegistry(
                Map.copyOf(
                        new SourceLoader(
                                new SectionLoader(files).load(),
                                traitFactory,
                                dispatcher,
                                provider
                        ).load()
                )
        );
    }

}
