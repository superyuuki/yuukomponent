package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.behavior.Event;

import java.util.Optional;

public interface StatCorrectness extends Event {

    void fail(String string);
    boolean isCorrect();

    Optional<String> missingKey();

}
