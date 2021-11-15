package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public interface TxRunnable {

    void run(Transaction tx) throws YuuKomponentFailure;

}
