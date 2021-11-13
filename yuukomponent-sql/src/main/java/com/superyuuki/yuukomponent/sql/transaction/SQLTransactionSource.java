package com.superyuuki.yuukomponent.sql.transaction;

import com.superyuuki.yuukomponent.api.exception.UncheckedFailure;
import com.superyuuki.yuukomponent.api.exception.YuuKomponentFailure;
import com.superyuuki.yuukomponent.api.transactional.TransactionSource;
import com.superyuuki.yuukomponent.api.transactional.TxRunnable;
import com.superyuuki.yuukomponent.api.transactional.TxSupplier;
import space.arim.jdbcaesar.JdbCaesar;
import space.arim.omnibus.util.concurrent.CentralisedFuture;
import space.arim.omnibus.util.concurrent.FactoryOfTheFuture;

/**
 * If this class makes no sense to the average backend developer i will separate it into non-anonymous classes
 */
public class SQLTransactionSource implements TransactionSource {

    private final FactoryOfTheFuture factory;
    private final JdbCaesar caesar;

    public SQLTransactionSource(FactoryOfTheFuture factory, JdbCaesar caesar) {
        this.factory = factory;
        this.caesar = caesar;
    }

    @Override
    public CentralisedFuture<?> runTx(TxRunnable consumer) {
        return factory.runAsync(() -> caesar.transaction().body((a, b) -> {
            try {
                consumer.run(new SQLTransaction(a));
            } catch (YuuKomponentFailure e) {
                throw new UncheckedFailure(e);
            }

            return null;
        }).execute());
    }

    @Override
    public <T> CentralisedFuture<T> calcTx(TxSupplier<T> supplier) {
        return factory.supplyAsync(() -> caesar.transaction().body((a, b) -> {
            try {
                return supplier.supply(new SQLTransaction(a));
            } catch (YuuKomponentFailure e) {
                throw new UncheckedFailure(e);
            }
        }).execute());
    }
}
