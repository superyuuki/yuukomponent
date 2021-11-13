package com.superyuuki.yuukomponent.api.transactional;

import space.arim.omnibus.util.concurrent.CentralisedFuture;

import java.util.function.Consumer;
import java.util.function.Function;

public interface TransactionSource {

    CentralisedFuture<?> runTx(TxRunnable consumer);
    <T> CentralisedFuture<T> calcTx(TxSupplier<T> function);

}
