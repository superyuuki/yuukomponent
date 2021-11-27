package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentException;

/**
 * Exception thrown when no component definition exists in configuration with a specific identifier
 */
public class NoSuchDefinitionException extends YuuKomponentException {

    public NoSuchDefinitionException(String message) {
        super("No such component exists for id: " + message);
    }

    @Override
    public String solution() {
        return "Check the spelling of any similar components or create a component with that type to replace the one that does not exist / was removed";
    }

}
