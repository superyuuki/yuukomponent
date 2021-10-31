package com.superyuuki.yuukomponent.core.behavior.standard;

import com.superyuuki.yuukomponent.core.stat.StatDef;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;
import com.superyuuki.yuukomponent.core.behavior.TypedBehavior;

public class DefStatBehavior implements TypedBehavior<StatObject> {

    private final StatDef def;

    public DefStatBehavior(StatDef def) {
        this.def = def;
    }

    @Override
    public void handle(StatObject event) {
        //def.export(event)
    }
}
