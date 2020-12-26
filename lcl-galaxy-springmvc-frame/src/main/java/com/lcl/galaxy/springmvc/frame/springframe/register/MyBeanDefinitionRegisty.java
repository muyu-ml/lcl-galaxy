package com.lcl.galaxy.springmvc.frame.springframe.register;


import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;

public interface MyBeanDefinitionRegisty {

    void registry(String beanName, MyBeanDefinition beanDefinition);

    MyBeanDefinition getBeanDefinition(String beanName);
}
