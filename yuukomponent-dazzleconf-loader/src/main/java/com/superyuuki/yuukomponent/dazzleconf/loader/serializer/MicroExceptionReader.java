package com.superyuuki.yuukomponent.dazzleconf.loader.serializer;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;
import space.arim.dazzleconf.error.InvalidConfigException;

import java.io.IOException;

public interface MicroExceptionReader {

    void handle(String config, IOException ioException) throws StartupFailure;
    void handle(String config, InvalidConfigException invalidConfigException) throws StartupFailure;

}
