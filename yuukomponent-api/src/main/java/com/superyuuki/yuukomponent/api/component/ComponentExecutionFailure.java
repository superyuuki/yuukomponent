package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public abstract class ComponentExecutionFailure extends YuuKomponentFailure {

    public ComponentExecutionFailure(Throwable cause) {
        super(cause);
    }

    public ComponentExecutionFailure(String message) {
        super(message);
    }
}
