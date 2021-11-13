package com.superyuuki.yuukomponent.api.transactional;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public interface TxSupplier<T> {

    T supply(Transaction tx) throws YuuKomponentFailure;

}
