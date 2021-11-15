package com.superyuuki.yuukomponent.api.behavior;

import com.superyuuki.yuukomponent.api.behavior.error.NoSuchBehaviorValueFailure;
import com.superyuuki.yuukomponent.api.behavior.error.WrongTypeValueFailure;

public interface BehaviorReader {

    BehaviorSource source(ConfSection config) throws NoSuchBehaviorValueFailure, WrongTypeValueFailure;

}
