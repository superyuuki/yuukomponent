package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.dazzleconf.loader.error.*;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.*;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.nio.file.Path;

public class Extractor<C> {

    private final Class<C> clazz;
    private final Path path;
    private final String configName;

    public Extractor(Class<C> clazz, Path path, String configName) {
        this.clazz = clazz;
        this.path = path;
        this.configName = configName;
    }

    public C extract() throws StartupFailure {
        try {
            return new ConfigurationHelper<>(
                    path,
                    configName,
                    SnakeYamlConfigurationFactory.create(clazz, ConfigurationOptions.defaults(), new SnakeYamlOptions.Builder().build()))
                    .reloadConfigData();
        } catch (InvalidConfigException | IOException e) {
            //elseif not inherently bad here, since there is no switch alternative and no oop principle will make this better
            //furthermore not violating "don't catch everything" since i'm just wrapping exceptions in a way that ties them
            //meaningfully to yuukomp



            throw new UnspecifiedConfigFailure(e.getMessage());

            //else
        }
    }

}
