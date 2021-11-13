package com.superyuuki.yuukomponent.core.spi;

import com.superyuuki.yuukomponent.api.Spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class SpiRetriever<L extends Spi<S>,S> {

    private final Class<L> spi;
    private final Class<S> service;

    public SpiRetriever(Class<L> spi, Class<S> service) {
        this.spi = spi;
        this.service = service;
    }

    public List<S> loadServices() {
        ClassLoader classLoader = service.getClassLoader();
        ServiceLoader<L> loader = ServiceLoader.load(spi, classLoader);

        Iterator<L> it = loader.iterator();
        if (!it.hasNext()) {
            return new ArrayList<>();
        }
        List<S> services = new ArrayList<>();
        do {
            services.add(it.next().provide());
        } while (it.hasNext());

        return services;
    }
}
