package com.superyuuki.yuukomponent.api.inbuilt;

import com.superyuuki.yuukomponent.api.inbuilt.stat.StatNotPresentException;
import com.superyuuki.yuukomponent.api.inbuilt.stat.StatObject;

public interface Key<T> {

    T get(StatObject object) throws StatNotPresentException;


}
