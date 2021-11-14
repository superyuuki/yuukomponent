package com.superyuuki.yuukomponent.api.transactional.func;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;
import com.superyuuki.yuukomponent.api.transactional.Transaction;

public interface TxSupplier<T> {

    T supply(Transaction tx) throws YuuKomponentFailure;

}
