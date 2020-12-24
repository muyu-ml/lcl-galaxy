package com.lcl.galaxy.spring.frame.V3.beanfactory.support;

import com.lcl.galaxy.spring.frame.V2.domain.MyBeanDefinition;
import com.lcl.galaxy.spring.frame.V2.domain.MyPropertyValue;
import com.lcl.galaxy.spring.frame.V2.domain.MyRuntimeBeanReference;
import com.lcl.galaxy.spring.frame.V2.domain.MyTypedStringValue;
import com.lcl.galaxy.spring.frame.V3.aware.MyAware;
import com.lcl.galaxy.spring.frame.V3.aware.MyBeanFactoryAware;
import com.lcl.galaxy.spring.frame.V3.beanfactory.MyAutowireCapableBeanFactory;
import com.lcl.galaxy.spring.frame.V3.utils.ReflectUtils;

import java.util.List;

public abstract class MyAbstructAutowireCapableBeanFactory extends MyAbstractBeanFactory implements MyAutowireCapableBeanFactory {
    @Override
    public Object createBean(String beanName, MyBeanDefinition beanDefinition) {
        Class<?> clazz = getResolvedClass(beanDefinition);
        Object object = createInstance(clazz);
        populateBean(object, beanDefinition);
        initalBean(object, beanDefinition);
        return object;
    }

    /**
     * bean的初始化
     * @param object
     * @param beanDefinition
     */
    private void initalBean(Object object, MyBeanDefinition beanDefinition) {
        //aware接口处理
        if(object instanceof MyAware){
            if(object instanceof MyBeanFactoryAware){
                ((MyBeanFactoryAware)object).setBeanFactory(this);
            }
        }
        //对实现了IniallizingBean接口的类，调用他的afterProperties方法
        //如果Bean中init-method属性有值，则调用指定的方法
        initMethod(object, beanDefinition);
    }

    private void initMethod(Object object, MyBeanDefinition beanDefinition) {
        String method = beanDefinition.getInitMethod();
        if(method != null && !"".equals(method)){
            ReflectUtils.invokeMethod(object, method);
        }
    }

    private Object createInstance(Class<?> clazz){
        //如果有实例工厂，则通过实例工厂创建Bean实例
        //如果有工厂方法，则通过工厂方法创建Bean实例
        //如果都没有，则使用构造函数创建Bean实例
        return ReflectUtils.createObject(clazz);
    }

    private Class<?> getResolvedClass(MyBeanDefinition beanDefinition){
        String clazzName = beanDefinition.getClazzName();
        try {
            Class<?> clazz = Class.forName(clazzName);
            return clazz;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为对象赋值
     * @param object
     * @param beanDefinition
     */
    private void populateBean(Object object, MyBeanDefinition beanDefinition) {
        List<MyPropertyValue> propertyValueList = beanDefinition.getPropertyValueList();
        for (MyPropertyValue propertyValue: propertyValueList) {
            String name = propertyValue.getName();
            Object value = propertyValue.getTypedStringValue();
            Object valueToUse = null;
            if(value instanceof MyTypedStringValue){
                MyTypedStringValue myTypedStringValue = (MyTypedStringValue) value;
                String stringValue = myTypedStringValue.getValue();
                Class<?> targetType = myTypedStringValue.getTargetType();
                if(targetType == Integer.class){
                    valueToUse = Integer.parseInt(stringValue);
                }else if(targetType == String.class){
                    valueToUse = String.valueOf(stringValue);
                }else{

                }
            }else if(value instanceof MyRuntimeBeanReference){
                MyRuntimeBeanReference myRuntimeBeanReference = (MyRuntimeBeanReference) value;
                valueToUse = getBean(myRuntimeBeanReference.getRef());
            }
            ReflectUtils.setProperty(object, name, valueToUse);
        }
    }

}
