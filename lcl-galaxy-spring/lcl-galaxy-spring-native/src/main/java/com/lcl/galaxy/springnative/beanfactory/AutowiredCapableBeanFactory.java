package com.lcl.galaxy.springnative.beanfactory;

import com.lcl.galaxy.springnative.definition.BeanDefinition;

/**
 * 具有创建Bean的功能，创建Bean就需要对Bean进行装配
 *
 * @author 灭霸詹
 *
 */
public interface AutowiredCapableBeanFactory extends BeanFactory {

    /**
     * 创建Bean实例
     *
     * @param beanName
     * @param beanDefinition
     * @return
     */
    Object createBean(String beanName, BeanDefinition beanDefinition);

}
