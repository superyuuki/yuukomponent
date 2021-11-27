package com.superyuuki.yuukomponent.api.addons.error;

public class TooManyImplementingAddonsFailure extends AddonValidityFailure {
    public TooManyImplementingAddonsFailure(Class<?> message) {
        super(String.format("A query looking for an addon of type: %s found too many addons advertising implementation!", message));
    }

    @Override
    public String solution() {
        return "You have two conflicting addons that implement a mutually exclusive addon feature. Remove one.";
    }
}
