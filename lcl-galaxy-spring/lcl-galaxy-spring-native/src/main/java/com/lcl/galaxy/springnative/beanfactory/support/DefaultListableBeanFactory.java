package com.lcl.galaxy.springnative.beanfactory.support;

import com.lcl.galaxy.springnative.definition.BeanDefinition;
import com.lcl.galaxy.springnative.registry.BeanDefinitionRegistry;
import com.lcl.galaxy.springnative.registry.support.DefaultSingletonBeanRegistry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * beanfactory 的集大成实现类
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitions = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitions.get(beanName);
    }


    @Override
    public <T> List<T> getBeansByType(Class<?> type) {
        return null;
    }

    @Override
    public List<String> getBeanNamesByType(Class<?> type) {
        return null;
    }
}
