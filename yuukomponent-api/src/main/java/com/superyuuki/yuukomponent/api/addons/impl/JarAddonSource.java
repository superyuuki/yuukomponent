package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.AddonSource;
import com.superyuuki.yuukomponent.api.addons.error.BadAddonClassFailure;
import com.superyuuki.yuukomponent.api.addons.error.BadAddonIOFailure;

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

public class JarAddonSource implements AddonSource {

    private final URI pluginUri;

    public JarAddonSource(URI pluginUri) {
        this.pluginUri = pluginUri;
    }

    public JarAddonSource(Path path) {
        this.pluginUri = path.toUri();
    }

    @Override
    public Class<?>[] load() throws StartupFailure {
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
