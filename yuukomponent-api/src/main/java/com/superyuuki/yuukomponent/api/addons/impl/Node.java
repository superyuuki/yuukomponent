package com.superyuuki.yuukomponent.api.addons.impl;

/*-
 * #%L
 * PlugFace :: Core
 * %%
 * Copyright (C) 2017 - 2018 PlugFace
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * #L%
 */

import com.superyuuki.yuukomponent.api.StartupFailure;
import com.superyuuki.yuukomponent.api.addons.Addon;
import com.superyuuki.yuukomponent.api.addons.error.ConstructorInvokeFailure;
import com.superyuuki.yuukomponent.api.addons.error.HasParametersFailure;

import java.lang.reflect.Constructor;

public class Node {

    private final String name;
    private final Class<? extends Addon> refClass;

    public Node(String name, Class<? extends Addon> refClass) {
        this.name = name;
        this.refClass = refClass;
    }

    public String name() {
        return name;
    }

    public <T extends Addon> T construct() throws StartupFailure {

        for (Constructor<?> constructor : refClass.getConstructors()) {
            if (constructor.getParameterTypes().length > 0) {
                try {
                    return (T) constructor.newInstance();
                } catch (ReflectiveOperationException e) {
                    throw new ConstructorInvokeFailure(refClass, e);
                }
            }
        }

        throw new HasParametersFailure(refClass);

    }


    @Override
    public String toString() {
        return "Node{" +
                "class=" + refClass.getSimpleName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Node)) {
            return false;
        }
        Node node = (Node) o;

        return refClass.equals(node.refClass);
    }

    @Override
    public int hashCode() {
        return refClass.hashCode();
    }
}
