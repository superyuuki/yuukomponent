package com.superyuuki.yuukomponent.core;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.concurrent.ForkJoinTask;

public interface Behavior {

    void handle(Event event);

}
