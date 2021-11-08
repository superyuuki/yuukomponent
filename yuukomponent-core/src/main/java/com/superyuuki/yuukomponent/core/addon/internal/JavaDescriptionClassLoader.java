package com.superyuuki.yuukomponent.core.addon.internal;

import com.superyuuki.yuukomponent.api.addon.internal.CustomClassLoader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

public class JavaDescriptionClassLoader extends URLClassLoader implements CustomClassLoader {

    private final Set<CustomClassLoader> otherLoaders;

    public JavaDescriptionClassLoader(URL[] urls, Set<CustomClassLoader> otherLoaders) {
        super(urls);
        this.otherLoaders = otherLoaders;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return loadClassCheck(name, resolve, true);
    }

    @Override
    public Class<?> loadClassCheck(String name, boolean resolve, boolean checkOther)
            throws ClassNotFoundException {
        try {
            return super.loadClass(name, resolve);
        } catch (ClassNotFoundException ignored) {
            // Ignored: we'll try others
        }

        if (checkOther) {
            for (CustomClassLoader loader : otherLoaders) {
                if (loader != this) {
                    try {
                        return loader.loadClassCheck(name, resolve, false);
                    } catch (ClassNotFoundException ignored) {} //TODO Handle NotFoundException in addon class loader
                }
            }
        }

        throw new ClassNotFoundException(name);
    }

    @Override
    public void close() throws IOException {
        otherLoaders.remove(this);
    }
}
