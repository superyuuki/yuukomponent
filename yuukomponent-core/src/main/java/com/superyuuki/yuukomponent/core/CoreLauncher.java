package com.superyuuki.yuukomponent.core;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.addon.Feature;
import com.superyuuki.yuukomponent.api.addon.internal.AddonLoader;
import com.superyuuki.yuukomponent.core.spi.AddonSpiRetriever;

import java.util.ArrayList;
import java.util.List;

public class CoreLauncher {

    public void startup(Platform platform) {

        //load addons first
        List<AddonLoader> loaders = new AddonSpiRetriever(platform).load();

        //load configs after

    }

}
