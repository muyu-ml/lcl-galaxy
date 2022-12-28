package com.lcl.galaxy.spring.frame.V3.register;

public interface MySingletonRegistry {
    Object getSingletonBean(String beanName);

    void addSingleton(String beanName, Object object);
}
