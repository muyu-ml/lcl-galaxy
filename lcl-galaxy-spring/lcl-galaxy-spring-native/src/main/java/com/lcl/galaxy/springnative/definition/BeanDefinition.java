package com.lcl.galaxy.springnative.definition;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BeanDefinition {
    private String clazzName;
    private String beanName;
    private String initMethod;
    private String scope;
    private List<PropertyValue> propertyValues;

    private static final String SCOPE_SINGLETON = "singleton";
    private static final String SCOPE_PROTOTYPE = "prototype";


    public BeanDefinition(String clazzName, String beanName){
        this.clazzName = clazzName;
        this.beanName = beanName;
    }

    public void addPropertyValue(PropertyValue pv) {
        if(this.propertyValues == null){
            this.propertyValues = new ArrayList<>();
        }
        this.propertyValues.add(pv);
    }

    public boolean isSingleton() {
        return scope == null || "".equals(scope) || SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype(){
        return SCOPE_PROTOTYPE.equals(scope);
    }
}
