package com.superyuuki.yuukomponent.ideal;

public interface StatComponent extends RecursiveComponent {

    StatObject stats();
    StatObject stats(StatObject initialStats);

}
