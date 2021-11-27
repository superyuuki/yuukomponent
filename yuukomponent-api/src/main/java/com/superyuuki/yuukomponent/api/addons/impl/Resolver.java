package com.superyuuki.yuukomponent.api.addons.impl;

import com.superyuuki.yuukomponent.api.addons.impl.Node;
import com.superyuuki.yuukomponent.api.addons.error.AddonValidityFailure;

import java.util.List;

public interface Resolver {

    List<Node> resolve() throws AddonValidityFailure;

}
