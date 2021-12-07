package com.superyuuki.yuukomponent.paper;

import com.superyuuki.yuukomponent.api.Platform;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.Executor;

public class PaperPlatform implements Platform {

    private final Plugin plugin;

    public PaperPlatform(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String version() {
        return plugin.getServer().getVersion();
    }

    @Override
    public Executor asyncExecutor() {
        return r -> plugin.getServer().getScheduler().runTaskAsynchronously(plugin, r);
    }

    @Override
    public Executor syncExecutor() {
        //TODO research if this is a248's anti deadlock executor or not
        return plugin.getServer().getScheduler().getMainThreadExecutor(plugin);
    }
}
