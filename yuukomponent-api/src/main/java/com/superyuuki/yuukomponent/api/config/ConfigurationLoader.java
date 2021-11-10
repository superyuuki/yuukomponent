package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.config.error.BadConfigFailure;

import java.io.IOException;

public interface ConfigurationLoader {

    ComponentRegistry load() throws BadConfigFailure, IOException;

}
