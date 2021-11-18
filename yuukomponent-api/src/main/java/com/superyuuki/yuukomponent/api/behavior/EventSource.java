package com.superyuuki.yuukomponent.api.behavior;

import java.util.function.Supplier;

public interface EventSource<T extends Event> {

    void dispatch(Supplier<T> eventSupplier, )

}
