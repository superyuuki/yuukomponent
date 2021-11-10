package com.superyuuki.yuukomponent.dazzleconf.loader;

import space.arim.dazzleconf.annote.SubSection;

import java.util.Map;

public interface Config {

    Map<String, @SubSection ConfigurationSection> map();

}
