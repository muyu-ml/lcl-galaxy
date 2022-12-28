package com.lcl.galaxy.springnative.beanfactory;

import java.util.List;

/**
 * 具有列表化功能的一个BeanFactory接口，它可以以列表的形式展示容器中的bean实例获取其他
 *
 * @author 灭霸詹
 *
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 根据bean的类型，获取它以及子类型在容器中对应的bean实例
     *
     * @param type
     * @return
     */
    <T> List<T> getBeansByType(Class<?> type);

    /**
     * 根据bean的类型，获取它以及子类型在容器中对应的bean的名称
     * @param type
     * @return
     */
    List<String> getBeanNamesByType(Class<?> type);
}
