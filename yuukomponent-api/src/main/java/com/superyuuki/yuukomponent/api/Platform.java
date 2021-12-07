package com.superyuuki.yuukomponent.api;

import java.util.concurrent.Executor;

public interface Platform {

    String version();

    Executor asyncExecutor();
    Executor syncExecutor();

}
