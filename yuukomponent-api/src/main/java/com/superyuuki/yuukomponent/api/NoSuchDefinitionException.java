package com.superyuuki.yuukomponent.api;

import com.superyuuki.yuukomponent.api.YuuKomponentException;
import com.superyuuki.yuukomponent.api.YuuKomponentFailure;

/**
 * Exception thrown when no component definition exists in configuration with a specific identifier
 */
public class NoSuchDefinitionException extends YuuKomponentFailure {

    public NoSuchDefinitionException(String message) {
        super("No such component exists for id: " + message);
    }

}
