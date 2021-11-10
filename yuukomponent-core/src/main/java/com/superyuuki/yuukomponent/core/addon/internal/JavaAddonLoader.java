package com.superyuuki.yuukomponent.core.addon.internal;

import com.superyuuki.yuukomponent.api.addon.Addon;
import com.superyuuki.yuukomponent.api.addon.error.AddonIoFailure;
import com.superyuuki.yuukomponent.api.addon.error.ReflectiveFailure;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.api.addon.internal.CustomClassLoader;
import com.superyuuki.yuukomponent.api.addon.internal.Description;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Set;

public class JavaAddonLoader implements AddonLoader {

    private final Path path;
    private final Description description;
    private final Set<CustomClassLoader> customClassLoaders;

    public JavaAddonLoader(Path path, Description description, Set<CustomClassLoader> customClassLoaders) {
        this.path = path;
        this.description = description;
        this.customClassLoaders = customClassLoaders;
    }

    @Override
    public Addon load() throws StartupFailure {
        try {
            URL url = path.toUri().toURL();
            JavaDescriptionClassLoader loader = AccessController.doPrivileged((PrivilegedAction<JavaDescriptionClassLoader>) () -> new JavaDescriptionClassLoader(new URL[]{url}, loaders));
            customClassLoaders.add(loader);

            Class<?> clazz = loader.loadClass(description.mainClassName());

            return (Addon) clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new ReflectiveFailure(path.getFileName().toString(), e);
        } catch (IOException e) {
            throw new AddonIoFailure(path.getFileName().toString(), e);
        }
    }

    @Override
    public Description description() {
        return description;
    }
}
