package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.component.error.NoSuchTxPropertyException;

public interface Transaction {

    <T> T property(Class<T> property) throws NoSuchTxPropertyException;

}
