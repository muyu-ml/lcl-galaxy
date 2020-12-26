package com.lcl.galaxy.springmvc.frame.springframe.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

    /**
     * 通过反射获取对象
     * @param args
     * @return
     */
    public static Object createObject(Class<?> clazz, Object... args){
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射设置属性值
     * @param object
     * @param name
     * @param valueToUse
     */
    public static void setProperty(Object object, String name, Object valueToUse){
        Class<?> clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(object, valueToUse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过反射获取字段的类型
     * @param beanClassName
     * @param name
     * @return
     */
    public static Class<?> getTypeByFieldName(String beanClassName, String name){
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射执行init方法
     * @param object
     * @param initMethod
     */
    public static void invokeMethod(Object object, String initMethod){
        Class<?> clazz = object.getClass();
        try {
            Method declaredMethod = clazz.getDeclaredMethod(initMethod);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(initMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
