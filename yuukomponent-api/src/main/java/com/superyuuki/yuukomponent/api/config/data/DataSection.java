package com.superyuuki.yuukomponent.api.config.data;

import com.superyuuki.yuukomponent.api.config.NoSuchValueException;

/**
 * Section of configuration dedicated to a configuration section's held data
 */
public interface DataSection {

    //TODO add more

    int intVal(String key) throws NoSuchValueException, ClassCastException;
    String strVal(String key) throws NoSuchValueException, ClassCastException;
    double doubleVal(String key) throws NoSuchValueException, ClassCastException;

}
