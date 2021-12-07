package com.superyuuki.yuukomponent.paper;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.AddonManager;
import com.superyuuki.yuukomponent.api.addons.BasicBootstrap;
import com.superyuuki.yuukomponent.api.addons.Bootstrap;
import com.superyuuki.yuukomponent.api.addons.ClassAddonSource;
import com.superyuuki.yuukomponent.api.addons.impl.BasicAddonManager;
import com.superyuuki.yuukomponent.api.addons.impl.BasicValidator;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.util.Objects;

public class YuukomponentPlugin extends JavaPlugin {

    @Override
    public void onLoad() {
        PaperPlatform platform = new PaperPlatform(this);
        Bootstrap bootstrap = new BasicBootstrap(platform);

        bootstrap.register(new ClassAddonSource(getDataFolder().toPath().resolve("addons"))); //add local plugins loader in

        getServer().getServicesManager().register(Bootstrap.class, bootstrap, this, ServicePriority.Highest);
    }

    @Override
    public void onEnable() {

        //STARTUP CHAIN
        try {
            AddonManager manager = Objects.requireNonNull(getServer().getServicesManager().load(Bootstrap.class)).launch();

            manager.getAllAddons()
        } catch (StartupFailure e) {
            //TODO
        }

    }
}
