package com.lcl.galaxy.springmvc.frame.handlermapping;

import com.lcl.galaxy.springmvc.frame.handler.QueryUserHandler;
import com.lcl.galaxy.springmvc.frame.handler.SaveUserHandler;
import com.lcl.galaxy.springmvc.frame.springframe.aware.MyBeanFactoryAware;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.MyBeanFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class MyBeanNameUrlHandlerMapping implements MyHandlerMapping, MyBeanFactoryAware {
    private MyBeanFactory beanFactory;

    private Map<String, Object> urlHanlderMap = new HashMap<>();

    public MyBeanNameUrlHandlerMapping(){
        init();
    }


    public void init(){
        urlHanlderMap.put("/query", new QueryUserHandler());
        urlHanlderMap.put("/save", new SaveUserHandler());
    }

    @Override
    public Object getHandler(HttpServletRequest request) {
        return urlHanlderMap.get(request.getRequestURI());
    }

    @Override
    public void setBeanFactory(MyBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
