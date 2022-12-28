package com.lcl.galaxy.spring.frame.V3.register;

import com.lcl.galaxy.spring.frame.V2.domain.MyBeanDefinition;

public interface MyBeanDefinitionRegisty {

    void registry(String beanName, MyBeanDefinition beanDefinition);

    MyBeanDefinition getBeanDefinition(String beanName);
}
