package com.lcl.galaxy.springmvc.frame.springframe.register.support;

import com.lcl.galaxy.springmvc.frame.springframe.register.MySingletonRegistry;

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
