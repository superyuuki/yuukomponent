package com.superyuuki.yuukomponent.api.component;

import com.superyuuki.yuukomponent.api.addons.Addon;

public interface StorageEntrypoint extends Addon {

    ComponentPool makeComponentPool();
    StructurePool makeStructurePool();
    TransactionSource makeTransactionSource();

}
