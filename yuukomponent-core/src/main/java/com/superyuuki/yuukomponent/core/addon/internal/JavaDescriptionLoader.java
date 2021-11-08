package com.superyuuki.yuukomponent.core.addon.internal;

import com.google.gson.Gson;
import com.superyuuki.yuukomponent.api.addon.error.InvalidAddonFileFailure;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.Description;
import com.superyuuki.yuukomponent.api.addon.internal.DescriptionLoader;
import com.superyuuki.yuukomponent.api.addon.processing.SerializedDescription;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class JavaDescriptionLoader implements DescriptionLoader {

    private final static Gson gson = new Gson();

    private final Path path;

    public JavaDescriptionLoader(Path path) {
        this.path = path;
    }

    @Override
    public Description load() throws StartupFailure, IOException {
        try (JarInputStream in = new JarInputStream(new BufferedInputStream(Files.newInputStream(path)))) {

            JarEntry entry;
            while ((entry = in.getNextJarEntry()) != null) {
                if (entry.getName().equals("yuukomponent-addon.json")) {
                    try (Reader pluginInfoReader = new InputStreamReader(in, StandardCharsets.UTF_8)) {
                        return gson.fromJson(pluginInfoReader, SerializedDescription.class);
                    }
                }
            }
        }

        throw new InvalidAddonFileFailure(path.getFileName().toString());
    }
}
