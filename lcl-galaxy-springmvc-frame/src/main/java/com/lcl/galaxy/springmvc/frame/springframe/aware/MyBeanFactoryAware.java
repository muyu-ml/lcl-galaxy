package com.lcl.galaxy.springmvc.frame.springframe.aware;

import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.MyBeanFactory;

public interface MyBeanFactoryAware extends MyAware {
    void setBeanFactory(MyBeanFactory beanFactory);
}
