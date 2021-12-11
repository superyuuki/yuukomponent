package com.superyuuki.yuukomponent.dazzleconf.loader.error;

import com.superyuuki.yuukomponent.api.trait.error.BadConfigFailure;

public class NoSuchBehaviorFailure extends BadConfigFailure {

    public NoSuchBehaviorFailure(String def, String behavior) {
        super(String.format("A component with definition type: %s has an invalid behavior type keyed: %s", def, behavior));
    }

    @Override
    public String solution() {
        return "Remove the offending behavior and/or check that your addons are loading correctly as this failure can be caused by a behavior not being loaded due to an addon initialization failure";
    }
}
