package com.superyuuki.yuukomponent.api.addons;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.error.BadAddonClassFailure;
import com.superyuuki.yuukomponent.api.addons.error.BadAddonIOFailure;
import com.superyuuki.yuukomponent.api.addons.impl.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassAddonSource implements AddonSource {

    private final URI pluginUri;
    private final Validator validator;

    public ClassAddonSource(URI pluginUri, Validator validator) {
        this.pluginUri = pluginUri;
        this.validator = validator;
    }

    public ClassAddonSource(Path path) {
        this(path.toUri(), new BasicValidator());
    }

    @Override
    public Map<String, Addon> load() throws StartupFailure {
        Class<?>[] allClasses = subload();

        List<Class<? extends Addon>> toFilter = new ArrayList<>();

        for (Class<?> clazz : allClasses) {
            validator.translate(clazz).ifPresent(toFilter::add);
        }

        Map<String, Addon> toReturn = new HashMap<>();

        for (Node node : new BasicResolver(toFilter, validator).resolve()) {
            toReturn.put(node.name(), node.construct());
        }

        return Map.copyOf(toReturn);
    }

    Class<?>[] subload() throws StartupFailure {
        try {
            final List<Class<?>> plugins = new ArrayList<>();
            final Path path = Paths.get(pluginUri);
            if (!Files.exists(path)) {
                throw new IllegalArgumentException("Path " + pluginUri + " does not exist");
            }

            if (!Files.isDirectory(path)) {
                throw new IllegalArgumentException("Path " + pluginUri + " is not a directory");
            }
            final Map<Path, URL> jarUrls = new HashMap<>();
            for (Path filePath : Files.newDirectoryStream(path)) {
                if (filePath.getFileName().toString().endsWith(".jar")) {
                    jarUrls.put(filePath, filePath.toUri().toURL());
                }
            }
            final PluginClassLoader cl = new PluginClassLoader(jarUrls.values().toArray(new URL[]{}));
            for (Path jarPath: jarUrls.keySet()) {
                final File file = jarPath.toAbsolutePath().toFile();
                final JarFile jar = new JarFile(file);
                for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
                    final JarEntry entry = entries.nextElement();
                    if (entry.isDirectory() || !entry.getName().endsWith(".class")) {
                        continue;
                    }
                    String className = entry.getName().substring(0, entry.getName().length() - 6);
                    className = className.replace('/', '.');
                    Class<?> clazz = Class.forName(className, true, cl);
                    plugins.add(clazz);
                }
            }
            return plugins.toArray(new Class<?>[0]);
        } catch (IOException e) {
            throw new BadAddonIOFailure(e);
        } catch (ClassNotFoundException e) {
            throw new BadAddonClassFailure(e);
        }

    }
}
