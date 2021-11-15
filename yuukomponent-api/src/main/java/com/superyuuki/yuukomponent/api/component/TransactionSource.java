package com.superyuuki.yuukomponent.api.component;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

public interface TransactionSource {

    CentralisedFuture<?> runTx(TxRunnable consumer);
    <T> CentralisedFuture<T> calcTx(TxSupplier<T> function);

}
