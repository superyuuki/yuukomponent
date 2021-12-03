package com.superyuuki.yuukomponent.spigot.persistence;

import com.superyuuki.yuukomponent.api.data.stat.def.StatDefRegistry;
import com.superyuuki.yuukomponent.api.entity.CompEntity;
import com.superyuuki.yuukomponent.api.entity.CompEntityDeserialize;
import com.superyuuki.yuukomponent.spigot.persistence.datacontainer.DataContainer;

public class CompEntityDeserializePDC implements CompEntityDeserialize {

    private final StatDefRegistry registry;
    private final DataContainer dataContainer;

    public CompEntityDeserializePDC(StatDefRegistry registry, DataContainer dataContainer) {
        this.registry = registry;
        this.dataContainer = dataContainer;
    }

    @Override
    public CompEntity deserialize() {




        return null;
    }
}
