package com.superyuuki.yuukomponent.api;

import java.util.UUID;

public interface BehaviorLoader {

    Behavior instantiate(UUID uuid);

}
