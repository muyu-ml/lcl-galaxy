package com.lcl.galaxy.springnative.beanfactory.support;

import com.lcl.galaxy.springnative.beanfactory.ListableBeanFactory;
import com.lcl.galaxy.springnative.definition.BeanDefinition;
import com.lcl.galaxy.springnative.registry.support.DefaultSingletonBeanRegistry;

import java.util.List;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ListableBeanFactory {
    @Override
    public Object getBean(String beanName) {
        // 1、获取缓存的单例bean实例
        Object singleton = getSingleton(beanName);
        // 2、如果不为空，直接返回
        if(singleton != null){
            return singleton;
        }
        // 3、如果没有就获取 BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        // 4、创建对象
        singleton = createBean(beanName, beanDefinition);
        // 5、如果是单例bean，就放入单例bean集合
        if(beanDefinition.isSingleton()){
            addSingleton(beanName, singleton);
        }
        return singleton;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);

    public abstract BeanDefinition getBeanDefinition(String beanName);

}
