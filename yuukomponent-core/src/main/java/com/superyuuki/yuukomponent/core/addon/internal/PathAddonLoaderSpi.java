package com.superyuuki.yuukomponent.core.addon.internal;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoaderSpi;
import com.superyuuki.yuukomponent.api.addon.internal.CustomClassLoader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PathAddonLoaderSpi implements AddonLoaderSpi {
    @Override
    public AddonLoader[] provide(Platform platform) throws StartupFailure {

        Set<CustomClassLoader> customClassLoaders = new CopyOnWriteArraySet<>();
        List<AddonLoader> loaders = new ArrayList<>();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(platform.addonsFolder(), p -> p.toFile().isFile() && p.toString().endsWith(".jar"))) {
            for (Path path : stream) {
                loaders.add(
                        new JavaAddonLoader(path, new JavaDescriptionLoader(path).load(), customClassLoaders)
                );
            }
        } catch (IOException ioException) {
            throw new UncheckedIOException(ioException);
        }

        return loaders.toArray(new AddonLoader[0]);
    }
}
