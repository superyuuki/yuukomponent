package com.superyuuki.yuukomponent.core.spi;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.api.addon.internal.spi.AddonLoaderSpi;
import com.superyuuki.yuukomponent.core.addon.AddonSorter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class AddonSpiRetriever {

    private final Platform platform;

    public AddonSpiRetriever(Platform platform) {
        this.platform = platform;
    }


    public List<AddonLoader> load() {
        ClassLoader classLoader = AddonLoader.class.getClassLoader();
        ServiceLoader<AddonLoaderSpi> loader = ServiceLoader.load(AddonLoaderSpi.class, classLoader);
        Iterator<AddonLoaderSpi> it = loader.iterator();
        if (!it.hasNext()) {
            return new ArrayList<>();
        }
        List<AddonLoaderSpi> providers = new ArrayList<>();
        do {
            providers.add(it.next());
        } while (it.hasNext());

        List<AddonLoader> loaders = new ArrayList<>();

        for (AddonLoaderSpi spi : providers) {

            try {
                loaders.addAll(List.of(spi.provide(platform)));
            } catch (StartupFailure startupFailure) {
                platform.reader().readFailure(startupFailure);
            }

        }

        try {
            return new AddonSorter(loaders).sort();
        } catch (StartupFailure e) {
            platform.reader().readFailure(e);
            return new ArrayList<>();
        }
    }

}
