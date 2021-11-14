package com.superyuuki.yuukomponent.api.addons.internal;

import com.superyuuki.yuukomponent.api.addons.internal.depends.Node;
import com.superyuuki.yuukomponent.api.addons.error.AddonValidityFailure;

import java.util.List;

public interface Resolver {

    List<Node> resolve() throws AddonValidityFailure;

}
