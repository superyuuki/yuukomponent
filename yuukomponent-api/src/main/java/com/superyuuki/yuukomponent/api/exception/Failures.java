package com.superyuuki.yuukomponent.api.exception;

public interface Failures {
    String SUPPORT = "Please join the YuuKomponent support discord and open an issue ticket. ";
    String COPY_RED = " Please copy the above text in dark red and include it in your report to support, it is a stacktrace that we need in order to tell what caused this error";
    String GENERIC_NO_COMPONENT = "Contact YuuKomponent support. This is a critical bug indicating that either you or your database deleted the component from storage, or that the component was never inserted into the database and YuuKomponent failed to report the issue!";
    String GENERIC_BAD_SCHEMA_CUR = "Contact YuuKomponent support. This is a critical bug indicating a failure of your database to maintain component relationships.";

}
