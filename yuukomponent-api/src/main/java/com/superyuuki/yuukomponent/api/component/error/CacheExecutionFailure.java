package com.superyuuki.yuukomponent.api.component.error;

import com.superyuuki.yuukomponent.api.component.ComponentExecutionFailure;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

import java.util.UUID;

public class CacheExecutionFailure extends ComponentExecutionFailure {
    public CacheExecutionFailure(UUID component, Throwable cause) {
        super(String.format("Something went wrong dispatching an event to a component with id: %s with exception: %s", component.toString(), cause));
    }

    @Override
    public String solution() {
        return "This is an internal error with the component cache. Please copy the red error above and contact support. Make sure to send the error to support.";
    }
}
