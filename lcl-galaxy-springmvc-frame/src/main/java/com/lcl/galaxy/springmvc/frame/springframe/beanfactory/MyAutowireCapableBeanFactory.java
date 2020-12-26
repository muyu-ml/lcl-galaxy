package com.lcl.galaxy.springmvc.frame.springframe.beanfactory;

import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;

/**
 * 具有创建Bean的功能，创建Bean就需要对Bean进行装配
 */
public interface MyAutowireCapableBeanFactory extends MyBeanFactory {
    Object createBean(String beanName, MyBeanDefinition myBeanDefinition);
}
