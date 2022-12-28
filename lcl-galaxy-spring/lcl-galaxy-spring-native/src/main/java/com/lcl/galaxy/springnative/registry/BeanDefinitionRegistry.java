package com.lcl.galaxy.springnative.registry;

import com.lcl.galaxy.springnative.definition.BeanDefinition;

/**
 * 负责对 beandefinition 的管理和注册
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    BeanDefinition getBeanDefinition(String beanName);
}
