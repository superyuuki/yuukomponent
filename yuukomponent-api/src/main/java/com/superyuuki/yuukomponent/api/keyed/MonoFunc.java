package com.superyuuki.yuukomponent.api.keyed;

import java.util.function.Function;

public interface MonoFunc<T> {

    /**
     * Calculates a value (possibly modifying another value)
     * @param isInitialValue whether an initial value is provided or not
     * @param initialValue the provided value to modify if present
     * @return the modified or new value
     */
    T calculate(boolean isInitialValue, T initialValue);

}
