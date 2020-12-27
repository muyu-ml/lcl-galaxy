package com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support;

import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;
import com.lcl.galaxy.springmvc.frame.springframe.register.MyBeanDefinitionRegisty;

import java.util.*;

public class MyDefaultListableBeanFactory extends MyAbstructAutowireCapableBeanFactory implements MyBeanDefinitionRegisty {


    private Map<String, MyBeanDefinition> beanDefinitions = new HashMap<>();

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
    public <T> List<T> getBeansByType(Class<?> clazz) {
        List<T> list = new ArrayList<>(beanDefinitions.size());
        for(MyBeanDefinition beanDefinition : beanDefinitions.values()){
            String clazzName = beanDefinition.getClazzName();
            Class<?> type = resolveClassName(clazzName);
            if(clazz.isAssignableFrom(type)){
                Object bean = getBean(beanDefinition.getBeanName());
                list.add((T) bean);
            }
        }
        return list;
    }

    private Class<?> resolveClassName(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MyBeanDefinition> getBeanDefinitions() {
        List<MyBeanDefinition> list = new ArrayList<>(beanDefinitions.size());
        Collection<MyBeanDefinition> values = beanDefinitions.values();
        for (MyBeanDefinition beanDefinition : values){
            list.add(beanDefinition);
        }
        return list;
    }
}
