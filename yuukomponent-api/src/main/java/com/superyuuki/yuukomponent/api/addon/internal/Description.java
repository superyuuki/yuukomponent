package com.superyuuki.yuukomponent.api.addon.internal;

import com.superyuuki.yuukomponent.api.addon.Feature;

import java.util.Collection;

public interface Description {

    String displayName();
    String version();
    String description();
    String author();

    Collection<Class<? extends Feature>> depends();
    Collection<Class<? extends Feature>> exports();

    String mainClassName();

}
