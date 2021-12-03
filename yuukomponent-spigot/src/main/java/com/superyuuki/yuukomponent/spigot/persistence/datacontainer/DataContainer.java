package com.superyuuki.yuukomponent.spigot.persistence.datacontainer;

import org.bukkit.persistence.PersistentDataContainer;

import java.util.List;

public interface DataContainer {

    String definition();
    PersistentDataContainer asPDC();

    //TODO systems
    //TODO children

}
