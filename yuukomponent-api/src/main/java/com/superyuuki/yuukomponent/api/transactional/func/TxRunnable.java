package com.superyuuki.yuukomponent.api.transactional.func;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;
import com.superyuuki.yuukomponent.api.transactional.Transaction;

public interface TxRunnable {

    void run(Transaction tx) throws YuuKomponentFailure;

}
