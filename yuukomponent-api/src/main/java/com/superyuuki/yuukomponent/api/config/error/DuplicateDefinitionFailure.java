package com.superyuuki.yuukomponent.api.config.error;

public class DuplicateDefinitionFailure extends BadConfigFailure {
    public DuplicateDefinitionFailure(String definition) {
        super(String.format("Two component definitions with the def id: %s have been defined", definition));
    }

    @Override
    public String solution() {
        return "Component definition identifiers must be unique. Please rename one and reload your configuration.";
    }
}