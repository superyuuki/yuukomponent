package com.superyuuki.yuukomponent.api.transactional;

import com.superyuuki.yuukomponent.api.addon.Feature;
import com.superyuuki.yuukomponent.api.transactional.func.TxRunnable;
import com.superyuuki.yuukomponent.api.transactional.func.TxSupplier;
import space.arim.omnibus.util.concurrent.CentralisedFuture;

public interface TransactionSourceFeature extends Feature {

    CentralisedFuture<?> runTx(TxRunnable consumer);
    <T> CentralisedFuture<T> calcTx(TxSupplier<T> function);

}
