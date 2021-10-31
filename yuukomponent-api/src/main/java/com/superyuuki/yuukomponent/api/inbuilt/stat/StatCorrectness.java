package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.Event;

public interface StatCorrectness extends Event {

    void fail(String string);
    boolean isCorrect();

    String missingKey();

}
