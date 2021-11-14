package com.superyuuki.yuukomponent.api.addons.error;

public class NotAddonAnnotationFailure extends AddonValidityFailure {
    public NotAddonAnnotationFailure(String message) {
        super(String.format("The addon class: %s is not annotated with an addon annotation!", message));
    }

    @Override
    public String solution() {
        return "Add an addon annotation to your addon class";
    }
}
