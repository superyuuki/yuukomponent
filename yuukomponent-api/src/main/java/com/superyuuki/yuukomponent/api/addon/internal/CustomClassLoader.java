package com.superyuuki.yuukomponent.api.addon.internal;

public interface CustomClassLoader {

    Class<?> loadClassCheck(String name, boolean resolve, boolean checkOther) throws ClassNotFoundException;

}
