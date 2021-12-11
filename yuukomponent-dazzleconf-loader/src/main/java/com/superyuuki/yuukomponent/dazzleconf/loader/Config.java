package com.superyuuki.yuukomponent.dazzleconf.loader;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.annote.SubSection;

import java.util.Map;

public interface Config {

    @ConfComments("All of your components")
    @ConfKey("components")
    Map<String, @SubSection ComponentSource> components();


}
