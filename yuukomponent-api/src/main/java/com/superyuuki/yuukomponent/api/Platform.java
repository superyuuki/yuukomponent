package com.superyuuki.yuukomponent.api;

import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.concurrent.ExecutorService;

public interface Platform {

    String version();
    FactoryOfTheFuture factory();
    ExecutorService service();

}
