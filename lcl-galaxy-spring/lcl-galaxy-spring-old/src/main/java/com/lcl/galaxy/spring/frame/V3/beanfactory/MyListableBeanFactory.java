package com.lcl.galaxy.spring.frame.V3.beanfactory;

import java.util.List;

public interface MyListableBeanFactory extends MyBeanFactory {

    List<String> getBeanNamesByType(Class<?> type);

    <T> List<T> getBeansByType(Class<?> type);

}
