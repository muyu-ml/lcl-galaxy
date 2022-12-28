package com.lcl.galaxy.springnative.registry.support;

import com.lcl.galaxy.springnative.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletons = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletons.get(beanName);
    }

    @Override
    public void addSingleton(String beanName, Object obj) {
        singletons.put(beanName, obj);
    }
}
