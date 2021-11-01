package com.superyuuki.yuukomponent.api.plugin;

/**
 * Represents a full functioned plugin
 */
public interface Plugin {

    void onLoad();
    void onTerminate();

}
