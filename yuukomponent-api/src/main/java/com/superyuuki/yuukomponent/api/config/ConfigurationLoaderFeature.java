package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.addon.Feature;
import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

import java.io.IOException;

public interface ConfigurationLoaderFeature extends Feature {

    ComponentRegistry load() throws BadConfigFailure;

}
