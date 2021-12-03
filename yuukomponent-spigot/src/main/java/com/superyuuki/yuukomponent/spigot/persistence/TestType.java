package com.superyuuki.yuukomponent.spigot.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class TestType implements PersistentDataType<PersistentDataContainer, String> {
    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return null;
    }

    @Override
    public Class<String> getComplexType() {
        return null;
    }

    @Override
    public PersistentDataContainer toPrimitive(String s, PersistentDataAdapterContext persistentDataAdapterContext) {
        return null;
    }

    @Override
    public String fromPrimitive(PersistentDataContainer persistentDataContainer, PersistentDataAdapterContext persistentDataAdapterContext) {
        return null;
    }
}
