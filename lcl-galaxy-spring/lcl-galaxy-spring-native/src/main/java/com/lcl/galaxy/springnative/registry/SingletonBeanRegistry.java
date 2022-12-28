package com.lcl.galaxy.springnative.registry;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);

    void addSingleton(String beanName, Object obj);
}
