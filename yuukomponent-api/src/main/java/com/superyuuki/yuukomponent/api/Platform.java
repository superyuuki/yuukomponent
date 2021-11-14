package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.addon.AddonManager;
import com.superyuuki.yuukomponent.api.addon.error.NoSuchBackingPlatformException;
import com.superyuuki.yuukomponent.api.exception.ExceptionReader;
import space.arim.omnibus.registry.Registry;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

public interface Platform {

    AddonManager registry(); //use as portal to other addons. No yuukomp services exposed via registry.
    ExceptionReader reader();
    ExecutorService executor();
    FactoryOfTheFuture factory();

    Path addonsFolder();
    Path componentsFolder();

    /**
     * Attempts to load the backing platform e.g. JavaPlugin if Spigot
     * @param clazz the class of platform
     * @param <T> type of platform
     * @return the platform
     * @throws NoSuchBackingPlatformException if the backing platform is of a different type
     */
    <T> T loadBacking(Class<T> clazz) throws NoSuchBackingPlatformException;

}
