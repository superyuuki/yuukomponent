package com.superyuuki.yuukomponent.api.design.behavior.standard;

import com.superyuuki.yuukomponent.api.design.stat.StatDef;
import com.superyuuki.yuukomponent.api.keyed.stat.map.StatObject;
import com.superyuuki.yuukomponent.api.design.behavior.TypedBehavior;

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
