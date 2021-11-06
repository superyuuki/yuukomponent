package com.superyuuki.yuukomponent.api.config;

import com.superyuuki.yuukomponent.api.NoSuchBehaviorException;

public interface BehaviorRegistry {

    BehaviorLoader loader(String behaviorIdentifier) throws NoSuchBehaviorException;

}
