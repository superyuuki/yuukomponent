package com.superyuuki.yuukomponent.api.inbuilt.stat;

import java.util.Optional;

public interface OptionalStat<T> {

    Optional<T> stat();
    T orDefault();

}
