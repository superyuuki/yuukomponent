package com.superyuuki.yuukomponent.dazzleconf.loader;

import com.superyuuki.yuukomponent.api.config.behavior.DataSection;
import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfKey;
import space.arim.dazzleconf.annote.SubSection;

import java.util.List;
import java.util.Map;

/**
 * Config representation of a component
 */
public interface ConfigurationSection {

    //TODO add slots ;)

    @ConfKey("behaviors")
    @ConfComments("The behaviors your component definition will use. Make sure all value requirements are met!")
    List<String> behaviors();

    @ConfKey("tags")
    @ConfComments("The tags to use")
    List<String> tags();

    @ConfKey("values")
    @ConfComments("The values your application will use")
    Map<String, Object> values();

}
