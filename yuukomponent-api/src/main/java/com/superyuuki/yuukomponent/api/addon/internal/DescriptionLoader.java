package com.superyuuki.yuukomponent.api.addon.internal;

import com.superyuuki.yuukomponent.api.addon.error.StartupFailure;

import java.io.IOException;

public interface DescriptionLoader {

    Description load() throws StartupFailure, IOException;

}
