package com.superyuuki.yuukomponent.api.addons.error;

public class NotAddonInterfaceFailure extends AddonValidityFailure {

    public NotAddonInterfaceFailure(String message) {
        super(String.format("A class: %s annotated with the Addon annotation does not implement any feature interface!", message));
    }


    @Override
    public String solution() {
        return "Implement the desired feature interface for your class. For a general purpose addon implement BasicEntrypoint.";
    }
}
