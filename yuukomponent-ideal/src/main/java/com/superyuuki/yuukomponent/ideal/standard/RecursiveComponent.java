package com.superyuuki.yuukomponent.ideal.standard;

import com.superyuuki.yuukomponent.ideal.Component;
import com.superyuuki.yuukomponent.ideal.StatObject;

import java.util.Map;

public interface RecursiveComponent extends Component {


    void handle(Object event, StatObject stats);

}
