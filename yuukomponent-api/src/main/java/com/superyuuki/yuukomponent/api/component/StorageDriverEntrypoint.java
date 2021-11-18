package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.component.newtype.CachedCompDriver;
import com.superyuuki.yuukomponent.api.component.newtype.CachedStructDriver;

public interface StorageDriverEntrypoint extends Addon {

    CachedCompDriver compdriver();
    CachedStructDriver structdriver();
}
