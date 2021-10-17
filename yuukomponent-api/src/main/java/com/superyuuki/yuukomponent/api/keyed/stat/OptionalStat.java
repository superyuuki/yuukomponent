package com.superyuuki.yuukomponent.api.keyed.stat;

import java.util.Optional;

public interface OptionalStat<T> extends Stat<T> {

    Optional<T> opt();

}
