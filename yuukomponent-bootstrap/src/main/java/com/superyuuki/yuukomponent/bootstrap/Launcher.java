package com.superyuuki.yuukomponent.bootstrap;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.AddonManager;
import com.superyuuki.yuukomponent.api.addons.impl.BasicValidator;
import com.superyuuki.yuukomponent.api.addons.impl.JarAddonSource;
import com.superyuuki.yuukomponent.api.addons.impl.QualityAddonDriver;
import com.superyuuki.yuukomponent.api.trait.BehaviorEntrypoint;
import com.superyuuki.yuukomponent.api.exception.ExceptionReader;

import java.nio.file.Path;

public class Launcher {

    void main(ExceptionReader reader, Platform platform, Path mainFolder) {

        try {
            AddonManager manager = new QualityAddonDriver(
                    platform,
                    new JarAddonSource(
                            mainFolder.resolve("addons")
                    ),
                    new BasicValidator()
            ).startup();

            manager.getAllAddons(BehaviorEntrypoint.class);


        } catch (StartupFailure e) {
            reader.readFailure(e);
        }


    }

}
