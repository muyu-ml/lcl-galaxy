package com.lcl.galaxy.springmvc.frame.springframe.beanfactory;

import java.util.List;

public interface MyListableBeanFactory extends MyBeanFactory {

    List<String> getBeanNamesByType(Class<?> type);

    <T> List<T> getBeansByType(Class<?> type);

}
