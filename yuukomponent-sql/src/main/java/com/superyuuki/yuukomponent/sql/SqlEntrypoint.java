package com.superyuuki.yuukomponent.sql;

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.AddonLimitedAccess;
import com.superyuuki.yuukomponent.api.component.storage.*;
import com.superyuuki.yuukomponent.api.component.storage.internal.ParentStorage;
import com.superyuuki.yuukomponent.api.component.storage.internal.StructureStorage;
import com.superyuuki.yuukomponent.api.component.storage.internal.TraitStorage;

public class SqlEntrypoint implements StorageEntrypoint {

    @Override
    public void onStartup(AddonLimitedAccess manager) throws StartupFailure {




    }

    @Override
    public Preloader preloader() {
        return null;
    }

    @Override
    public ParentStorage parents() {
        return null;
    }

    @Override
    public StructureStorage structures() {
        return null;
    }

    @Override
    public TraitStorage traits() {
        return null;
    }

    @Override
    public ComponentMutator componentMut() {
        return null;
    }

    @Override
    public TraitMutator traitMut() {
        return null;
    }
}
