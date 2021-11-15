package com.superyuuki.yuukomponent.api;

import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

import java.util.concurrent.ExecutorService;

public interface Platform {

    FactoryOfTheFuture factory();
    ExecutorService service();

}
