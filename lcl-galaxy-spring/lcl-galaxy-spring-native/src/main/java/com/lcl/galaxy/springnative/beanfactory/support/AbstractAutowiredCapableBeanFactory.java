package com.lcl.galaxy.springnative.beanfactory.support;

import com.lcl.galaxy.springnative.aware.Aware;
import com.lcl.galaxy.springnative.aware.BeanFactoryAware;
import com.lcl.galaxy.springnative.beanfactory.AutowiredCapableBeanFactory;
import com.lcl.galaxy.springnative.definition.BeanDefinition;
import com.lcl.galaxy.springnative.definition.PropertyValue;
import com.lcl.galaxy.springnative.definition.RuntimeBeanReference;
import com.lcl.galaxy.springnative.definition.TypedStringValue;
import com.lcl.galaxy.springnative.utils.ReflectUtils;

import java.util.List;

public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory implements AutowiredCapableBeanFactory {


    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition) {
        // 1、获取类型
        Class<?> classType = getClassType(beanDefinition.getClazzName());
        // 2、创建对象
        Object object = resolverClass(classType);
        // 3、属性填充
        populateProperties(object, beanDefinition);
        // 4、初始化
        initialBean(object, beanDefinition);
        return object;
    }

    private void initialBean(Object object, BeanDefinition beanDefinition) {
        // 1、对Aware接口的实现做处理
        if(object instanceof Aware){
            if(object instanceof BeanFactoryAware){
                BeanFactoryAware beanFactoryAware = (BeanFactoryAware) object;
                beanFactoryAware.setBeanFactory(this);
            }
        }
        // 2、对实现了InitializingBean接口的类，调用它的afterPropertiesSet方法
        // 3、如果bean标签中的init-method属性有值，则调用指定值对应的方法（AOP处理）
        initialMethod(object, beanDefinition);
    }

    private void initialMethod(Object object, BeanDefinition beanDefinition) {
        String initMethod = beanDefinition.getInitMethod();
        if (initMethod == null) {
            return;
        }
        // BeanPostProcessor执行前置处理方法
        ReflectUtils.invokeMethod(object, initMethod);
        // BeanPostProcessor执行后置处理方法（AOP逻辑的开始）
    }

    private void populateProperties(Object object, BeanDefinition beanDefinition){
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for(PropertyValue pv : propertyValues){
            String name = pv.getName();
            Object value = pv.getValue();
            Object valueToUse = null;
            if(value instanceof TypedStringValue){
                TypedStringValue typedStringValue = (TypedStringValue) value;
                Class<?> targetType = typedStringValue.getTargetType();
                String stringValue = typedStringValue.getValue();
                if(targetType == Integer.class){
                    valueToUse = Integer.parseInt(stringValue);
                }else if(targetType == String.class){
                    valueToUse = stringValue;
                }
            }else if(value instanceof RuntimeBeanReference){
                RuntimeBeanReference reference = (RuntimeBeanReference) value;
                String ref = reference.getRef();
                valueToUse = getBean(ref);
            }
            ReflectUtils.setProperty(object, name, valueToUse);
        }
    }

    private Object resolverClass(Class<?> classType){
        // 如果有工厂方法，则使用工厂方法进行创建实例
        // 如果有工厂bean，则使用工厂bean创建实例
        // 如果都没有，则使用构造方法创建实例
        Object object = ReflectUtils.createObject(classType);
        return object;
    }

    private Class<?> getClassType(String clazzName){
        Class<?> aClass = null;
        try {
            aClass = Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return aClass;
    }

}
