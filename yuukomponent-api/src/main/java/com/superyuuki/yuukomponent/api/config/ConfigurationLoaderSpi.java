package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.Platform;
import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

public interface ConfigurationLoaderSpi {

    ComponentRegistry provide(Platform platform) throws BadConfigFailure;

}
