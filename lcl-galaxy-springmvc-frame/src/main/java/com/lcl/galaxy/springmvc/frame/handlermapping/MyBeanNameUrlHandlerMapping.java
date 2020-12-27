package com.lcl.galaxy.springmvc.frame.handlermapping;

import com.lcl.galaxy.springmvc.frame.handler.QueryUserHandler;
import com.lcl.galaxy.springmvc.frame.handler.SaveUserHandler;
import com.lcl.galaxy.springmvc.frame.springframe.aware.MyBeanFactoryAware;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.MyBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support.MyDefaultListableBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.domain.MyBeanDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBeanNameUrlHandlerMapping implements MyHandlerMapping, MyBeanFactoryAware {
    private MyDefaultListableBeanFactory beanFactory;

    private Map<String, Object> urlHanlderMap = new HashMap<>();

    public void init(){
        try {
            List<MyBeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
            for (MyBeanDefinition beanDefinition : beanDefinitions){
                String beanName = beanDefinition.getBeanName();
                if(beanName.startsWith("/")){
                    urlHanlderMap.put(beanName, beanFactory.getBean(beanName));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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
