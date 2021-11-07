package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.component.ComponentSource;
import com.superyuuki.yuukomponent.api.component.UUIDProvider;
import com.superyuuki.yuukomponent.api.config.*;
import com.superyuuki.yuukomponent.api.blueprint.BehaviorSource;
import com.superyuuki.yuukomponent.api.config.behavior.BehaviorRegistry;
import com.superyuuki.yuukomponent.api.config.behavior.error.NoSuchBehaviorFailure;
import com.superyuuki.yuukomponent.api.config.error.*;
import com.superyuuki.yuukomponent.core.component.MappedComponentSource;
import com.superyuuki.yuukomponent.core.config.MappedComponentRegistry;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.*;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class DazzleConfigurationLoader implements ConfigurationLoader {

    private final Path[] files;
    private final UUIDProvider provider;
    private final BehaviorRegistry behaviorRegistry;

    public DazzleConfigurationLoader(Path[] files, UUIDProvider provider, BehaviorRegistry behaviorRegistry) {
        this.files = files;
        this.provider = provider;
        this.behaviorRegistry = behaviorRegistry;
    }


    /**
     * Loads the configuration using dazzleconf
     *
     * TODO Make the DazzleConfigurationLoader implementation less arcane
     * tags: low-priority
     *
     * The DazzleConf loader here is very hard to read and object-ize. An object oriented implementation is in order
     * but i am too lazy to implement it right now.
     *
     * @return registry
     * @throws BadConfigFailure if config bad
     * @throws IOException if exception
     */
    @Override
    public ComponentRegistry load() throws BadConfigFailure, IOException {

        Map<String, ConfigurationSection> sectionMap = new HashMap<>();

        for (Path path : files) {
            String fileName = path.getFileName().toString();

            Config config;

            try {
                config = new ConfigurationHelper<>(
                        path.getParent(),
                        fileName,
                        SnakeYamlConfigurationFactory.create(Config.class, ConfigurationOptions.defaults(), new SnakeYamlOptions.Builder().build()))

                        .reloadConfigData();

                for (Map.Entry<String, ConfigurationSection> entry : config.map().entrySet()) {
                    String s = entry.getKey();
                    ConfigurationSection configurationSection = entry.getValue();

                    if (sectionMap.containsKey(s)) throw new DuplicateDefinitionFailure(s);

                    sectionMap.put(s, configurationSection);
                }

            } catch (InvalidConfigException e) {
                //elseif not inherently bad here, since there is no switch alternative and no oop principle will make this better
                //furthermore not violating "don't catch everything" since i'm just wrapping exceptions in a way that ties them
                //meaningfully to yuukomp

                if (e instanceof BadValueException) {
                    throw new SyntaxFailure(fileName, ((BadValueException) e).getKey());
                } else if (e instanceof ConfigFormatSyntaxException) {
                    throw new FormatFailure(fileName);
                } else if (e instanceof ImproperEntryException) {
                    throw new SyntaxFailure(fileName, ((ImproperEntryException) e).getKey());
                }

                throw new UnspecifiedConfigFailure(e.getMessage());

                //else
            }
        }

        Map<String, ComponentSource> componentMap = new HashMap<>();

        for (Map.Entry<String, ConfigurationSection> sectionEntry : sectionMap.entrySet()) {
            String componentId = sectionEntry.getKey();
            ConfigurationSection section = sectionEntry.getValue();

            List<BehaviorSource> behaviors = new ArrayList<>();

            for (String behaviorId : section.behaviors()) {
                Optional<BehaviorSource> loader = behaviorRegistry.loader(behaviorId);

                if (loader.isEmpty()) throw new NoSuchBehaviorFailure(componentId, behaviorId);

            }

            ComponentSource loader = new MappedComponentSource(provider, section.data(), behaviors);
            componentMap.put(componentId, loader);
        }

        return new MappedComponentRegistry(Map.copyOf(componentMap));
    }

}
