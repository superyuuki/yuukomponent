package com.superyuuki.yuukomponent.api.transactional;

import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;

public interface TxRunnable {

    void run(Transaction tx) throws YuuKomponentFailure;

}
