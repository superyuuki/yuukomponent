package com.superyuuki.yuukomponent.dazzleconf.loader.serializer;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.trait.error.BadConfigIOFailure;
import com.superyuuki.yuukomponent.dazzleconf.loader.error.*;
import space.arim.dazzleconf.error.*;

import java.io.IOException;

public class BasicMEReader implements MicroExceptionReader {


    @Override
    public void handle(String config, IOException ioException) throws StartupFailure {
        throw new BadConfigIOFailure(ioException);
    }

    @Override
    public void handle(String config, InvalidConfigException invalidConfigException) throws StartupFailure {


        if (invalidConfigException instanceof MissingKeyException) {
            throw new MissingKeyFailure(config, ((MissingKeyException) invalidConfigException).getKey());
        } else if (invalidConfigException instanceof MissingValueException) {
            throw new MissingValueFailure(config, ((MissingValueException) invalidConfigException).getKey());
        } else if (invalidConfigException instanceof BadValueException) {
            switch (((BadValueException) invalidConfigException).getKey()) {
                case "type:missing":
                    throw new MissingKeyFailure(config, "type");
                case "type:bad":
                    throw new NoSuchBehaviorFailure(invalidConfigException.getMessage(), "type");
            }
        } else if (invalidConfigException instanceof ConfigFormatSyntaxException) {
            throw new FormatFailure(config);
        } else if (invalidConfigException instanceof ImproperEntryException) {
            throw new SyntaxFailure(config, ((ImproperEntryException) invalidConfigException).getKey());
        }

        throw new UnspecifiedConfigFailure(invalidConfigException);
    }
}
