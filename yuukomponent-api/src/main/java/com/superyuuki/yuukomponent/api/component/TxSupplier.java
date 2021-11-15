package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public interface TxSupplier<T> {

    T supply(Transaction tx) throws YuuKomponentFailure;

}
