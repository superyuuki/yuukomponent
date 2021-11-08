package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.config.error.FormatFailure;
import com.superyuuki.yuukomponent.api.config.error.SyntaxFailure;
import com.superyuuki.yuukomponent.api.config.error.UnspecifiedConfigFailure;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.error.BadValueException;
import space.arim.dazzleconf.error.ConfigFormatSyntaxException;
import space.arim.dazzleconf.error.ImproperEntryException;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlOptions;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigExtractor {

    private final Path parent;
    private final String fileName;

    public ConfigExtractor(Path parent, String fileName) {
        this.parent = parent;
        this.fileName = fileName;
    }

    public Config extract() throws BadConfigFailure, IOException {
        try {
            return new ConfigurationHelper<>(
                    parent,
                    fileName,
                    SnakeYamlConfigurationFactory.create(Config.class, ConfigurationOptions.defaults(), new SnakeYamlOptions.Builder().build()))
                    .reloadConfigData();
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

}
