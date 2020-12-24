package com.lcl.galaxy.spring.frame.V3.beanfactory;

import com.lcl.galaxy.spring.frame.V2.domain.MyBeanDefinition;

/**
 * 具有创建Bean的功能，创建Bean就需要对Bean进行装配
 */
public interface MyAutowireCapableBeanFactory extends MyBeanFactory {
    Object createBean(String beanName, MyBeanDefinition myBeanDefinition);
}
