package com.superyuuki.yuukomponent.api.plugin;

public interface NexusRegistry {

    <T extends Archonexus> T retrieve(Class<T> clazz);

}
