package com.lcl.galaxy.springnative.aware;

import com.lcl.galaxy.springnative.beanfactory.BeanFactory;

public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory);
}
