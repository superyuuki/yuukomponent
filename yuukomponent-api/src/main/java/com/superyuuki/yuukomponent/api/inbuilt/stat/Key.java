package com.superyuuki.yuukomponent.api.inbuilt.stat;

import com.superyuuki.yuukomponent.api.inbuilt.stat.StatNotPresentException;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;

public interface Key<T> {

    /**
     * @return the value used to start the stat query
     */
    T defaultValue();

    /**
     * Gets a value from a stat query
     * @param object
     * @return
     */
    T get(StatObject object);


}
