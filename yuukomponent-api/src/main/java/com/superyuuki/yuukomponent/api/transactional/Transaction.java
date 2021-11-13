package com.superyuuki.yuukomponent.api.transactional;

public interface Transaction {

    <T> T property(Class<T> property) throws NoSuchTxPropertyException;

}
