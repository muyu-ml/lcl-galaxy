package com.lcl.galaxy.design.pattern.singleton;

public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance(){
        return INSTANCE;
    }
}
