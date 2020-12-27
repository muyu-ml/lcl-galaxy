package com.lcl.galaxy.springmvc.frame.handlermapping;

import com.lcl.galaxy.springmvc.frame.annotation.MyController;
import com.lcl.galaxy.springmvc.frame.annotation.MyRequestMapping;
import com.lcl.galaxy.springmvc.frame.handler.MyHandlerMethod;
import com.lcl.galaxy.springmvc.frame.springframe.aware.MyBeanFactoryAware;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.MyBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support.MyDefaultListableBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRequestMappingHandlerMapping implements MyHandlerMapping, MyBeanFactoryAware {
    private MyDefaultListableBeanFactory beanFactory;

    private Map<String, MyHandlerMethod> urlHanlderMap = new HashMap<>();

    public void init(){
        //获取并遍历所有的BeanDefinition
        List<MyBeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
        for (MyBeanDefinition beanDefinition : beanDefinitions){
            String clazzName = beanDefinition.getClazzName();
            Class<?> clazz = resolveClass(clazzName);
            //判断Beandefinition是否使用了MyController注解
            if(isHandler(clazz)){
                //如果使用了MyController注解，则获取它的所有方法
                Method[] declaredMethods = clazz.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    MyRequestMapping classAnnotation = clazz.getAnnotation(MyRequestMapping.class);
                    String classUri = classAnnotation.value();

                    //判断方法是否使用了MyRequestMapping注解
                    if(method.isAnnotationPresent(MyRequestMapping.class)){
                        //获取MyRequestMapping注解中配置的值
                        MyRequestMapping methodAnnotation = method.getAnnotation(MyRequestMapping.class);
                        String methodUri = methodAnnotation.value();
                        //封装MyHandlerMethod对象（controller + method）
                        MyHandlerMethod handlerMethod = new MyHandlerMethod(beanFactory.getBean(beanDefinition.getBeanName()), method);
                        urlHanlderMap.put(classUri + methodUri, handlerMethod);
                    }
                }
            }
        }
    }

    private boolean isHandler(Class<?> clazz) {
        return clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyRequestMapping.class);
    }


    private Class<?> resolveClass(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return urlHanlderMap.get(request.getRequestURI());
    }

    @Override
    public void setBeanFactory(MyBeanFactory beanFactory) {
        this.beanFactory = (MyDefaultListableBeanFactory) beanFactory;
    }
}
