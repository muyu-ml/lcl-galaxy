package com.lcl.galaxy.springmvc.frame.springframe.register;

public interface MySingletonRegistry {
    Object getSingletonBean(String beanName);

    void addSingleton(String beanName, Object object);
}
