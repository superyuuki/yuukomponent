package com.superyuuki.yuukomponent.api.exception;

public interface ExceptionReader {

    /**
     * Parse an exception in a idiot readable way so that server owners don't think the plugin is
     * spamming ""eRroRs"" when exceptions are thrown because god forbid a server owner see an exception
     * and think it's not their own fault (config errors, invalid operations, unsafe operations, etc)
     *
     * @param exception the exception
     */
    void readException(Solvable exception);
    void readFailure(Solvable failure);

}
