package com.superyuuki.yuukomponent.api.slot;

import com.superyuuki.yuukomponent.api.component.Component;

import java.util.Optional;

public interface Slot {

    Component get();
    boolean has();

    Optional<Component> asOpt();

}
