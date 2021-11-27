package com.superyuuki.yuukomponent.api.exception;

/**
 * Parent exception
 */
public abstract class YuuKomponentException extends RuntimeException implements Solvable {

    public YuuKomponentException(String message) {
        super(message);
    }

    public YuuKomponentException(Throwable cause) {
        super(cause);
    }

}
