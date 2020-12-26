package com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support;

import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.MyListableBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;
import com.lcl.galaxy.springmvc.frame.springframe.register.support.MyDefaultSingletonBeanRegistory;

public abstract class MyAbstractBeanFactory extends MyDefaultSingletonBeanRegistory implements MyListableBeanFactory {

    @Override
    public Object getBean(String beanName) {

        Object bean = getSingletonBean(beanName);
        if(bean != null){
            return bean;
        }
        MyBeanDefinition beanDefinition = getBeanDefinition(beanName);
        if(beanDefinition == null){
            return null;
        }

        if(beanDefinition.isSingleton()){
            bean = createBean(beanName, beanDefinition);
            addSingleton(beanName, beanDefinition);
        }else if(beanDefinition.isPrototype()){
            bean = createBean(beanName, beanDefinition);
        }
        return bean;
    }

    public abstract Object createBean(String beanName, MyBeanDefinition beanDefinition);

    public abstract MyBeanDefinition getBeanDefinition(String beanName);

}
