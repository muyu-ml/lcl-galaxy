package com.lcl.galaxy.spring.frame.V3.beanfactory.support;

import com.lcl.galaxy.spring.frame.V2.domain.MyBeanDefinition;
import com.lcl.galaxy.spring.frame.V3.register.MyBeanDefinitionRegisty;
import org.apache.commons.collections.map.HashedMap;

import java.util.List;
import java.util.Map;

public class MyDefaultListableBeanFactory extends MyAbstructAutowireCapableBeanFactory implements MyBeanDefinitionRegisty {


    private Map<String, MyBeanDefinition> beanDefinitions = new HashedMap();

    @Override
    public void registry(String beanName, MyBeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }

    @Override
    public MyBeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        return null;
    }

    @Override
    public <T> List<T> getBeansByType(Class<?> type) {
        return null;
    }
}
