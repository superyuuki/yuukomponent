package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;
import com.superyuuki.yuukomponent.api.config.error.DuplicateDefinitionFailure;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SectionLoader {

    private final Path[] files;

    SectionLoader(Path[] files) {
        this.files = files;
    }

    Map<String, ConfigurationSection> load() throws BadConfigFailure, IOException {
        Map<String, ConfigurationSection> sectionMap = new HashMap<>();

        for (Path path : files) {
            String fileName = path.getFileName().toString();

            Config config = new ConfigExtractor(path.getParent(), fileName).extract();

            for (Map.Entry<String, ConfigurationSection> entry : config.map().entrySet()) {
                String s = entry.getKey();

                if (sectionMap.containsKey(s)) throw new DuplicateDefinitionFailure(s);

                sectionMap.put(s, entry.getValue());
            }
        }

        return sectionMap;
    }

}
