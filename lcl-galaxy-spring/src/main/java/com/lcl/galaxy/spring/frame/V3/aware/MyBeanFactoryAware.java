package com.lcl.galaxy.spring.frame.V3.aware;

import com.lcl.galaxy.spring.frame.V3.beanfactory.MyBeanFactory;

public interface MyBeanFactoryAware extends MyAware {
    void setBeanFactory(MyBeanFactory beanFactory);
}
