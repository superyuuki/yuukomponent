package com.superyuuki.yuukomponent.core.config.component;

import com.superyuuki.yuukomponent.api.NoSuchBehaviorException;
import com.superyuuki.yuukomponent.api.config.*;

public interface RegistryLoader {

    ComponentRegistry load() throws BadConfigFailure, NoSuchBehaviorException;

}
