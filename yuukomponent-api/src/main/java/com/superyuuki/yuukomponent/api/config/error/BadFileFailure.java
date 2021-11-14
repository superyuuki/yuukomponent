package com.superyuuki.yuukomponent.api.config.error;

public class BadFileFailure extends BadConfigFailure {
    public BadFileFailure(String configFile, Throwable message) {
        super(String.format("An IOException was thrown trying to read config: %s with exception: %s", configFile, message));
    }

    @Override
    public String solution() {
        return "Your config file is corrupted. Please remake it and try again. Additionally, report this error including the red text to YuuKomponent support.";
    }
}
