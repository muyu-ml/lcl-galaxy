package com.lcl.galaxy.spring.frame.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MyBeanDefinition {

    private String clazzName;

    private String scope;

    private String beanName;

    private String initMethod;

    List<MyPropertyValue> propertyValueList = new ArrayList<>();

    private final String SCOPE_SINGLETON = "singleton";
    private final String SCOPE_PROTOTYPE = "prototype";

    public MyBeanDefinition(String clazzName, String beanName) {
        this.clazzName = clazzName;
        this.beanName = beanName;
    }

    public boolean isSingleton(){
        if(SCOPE_SINGLETON.equals(scope)){
            return true;
        }
        return false;
    }

    public boolean isPrototype(){
        if(SCOPE_PROTOTYPE.equals(scope)){
            return true;
        }
        return false;
    }

    public void addPropertyValue(MyPropertyValue pv) {
        propertyValueList.add(pv);
    }
}
