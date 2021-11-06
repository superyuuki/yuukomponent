package com.superyuuki.yuukomponent.api.config.data;

import java.util.List;

/**
 * Config representation of a component
 */
public interface ConfigSection {

    //TODO add slots ;)

    List<String> behaviors();
    DataSection data();

}
