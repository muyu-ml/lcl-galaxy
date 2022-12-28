package com.lcl.galaxy.spring.frame.V3.register.support;

import com.lcl.galaxy.spring.frame.V3.register.MySingletonRegistry;

import java.util.HashMap;
import java.util.Map;

public class MyDefaultSingletonBeanRegistory implements MySingletonRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void addSingleton(String beanName, Object object) {
        singletonObjects.put(beanName, object);
    }
}
