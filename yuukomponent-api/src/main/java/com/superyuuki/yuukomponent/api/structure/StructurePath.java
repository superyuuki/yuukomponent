package com.superyuuki.yuukomponent.api.structure;

import java.util.Collection;

//TODO make this better
public interface StructurePath {

    String here();
    Collection<StructurePath> below();

}
