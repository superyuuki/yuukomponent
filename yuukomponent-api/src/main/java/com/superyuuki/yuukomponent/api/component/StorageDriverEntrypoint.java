package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.component.low.CompPool;
import com.superyuuki.yuukomponent.api.component.low.StructPool;

public interface StorageDriverEntrypoint extends Addon {

    CompPool compdriver();
    StructPool structdriver();
}
