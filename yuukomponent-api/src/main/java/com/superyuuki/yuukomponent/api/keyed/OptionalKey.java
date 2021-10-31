package com.superyuuki.yuukomponent.api.keyed;

public record OptionalKey<T>(String identifier, Class<T> clazz, T defaultValue) {



}
