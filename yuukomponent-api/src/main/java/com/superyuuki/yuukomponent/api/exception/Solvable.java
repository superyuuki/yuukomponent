package com.superyuuki.yuukomponent.api.exception;

public interface Solvable {

    String solution();

    default boolean critical() { return false; }
    default Throwable wrapped() { throw new UnsupportedOperationException("non-critical solvable cannot display a wrapped exception!"); }

}
