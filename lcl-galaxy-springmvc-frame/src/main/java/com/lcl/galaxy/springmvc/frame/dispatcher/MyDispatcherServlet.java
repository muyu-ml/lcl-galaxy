package com.lcl.galaxy.springmvc.frame.dispatcher;

import com.lcl.galaxy.springmvc.frame.adapter.MyHandlerAdapter;
import com.lcl.galaxy.springmvc.frame.handlermapping.MyHandlerMapping;
import com.lcl.galaxy.springmvc.frame.springframe.beanfactory.support.MyDefaultListableBeanFactory;
import com.lcl.galaxy.springmvc.frame.springframe.reader.MyXmlBeanDefinitionReader;
import com.lcl.galaxy.springmvc.frame.springframe.resource.MyClassPathResource;
import com.lcl.galaxy.springmvc.frame.springframe.resource.MyResources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MyDispatcherServlet extends MyAbstructServlet {

    private final String SPRING_MVC_CONTEXT = "contextConfigLocation";

    private MyDefaultListableBeanFactory beanFactory;

    private List<MyHandlerMapping> handlerMappingList = new ArrayList<>();

    private List<MyHandlerAdapter> handlerAdapterList = new ArrayList<>();

    /**
     * Servelet自身的生命周期方法；servlet被实例化后就会执行初始化方法
     *
     * @param config
     */
    @Override
    public void init(ServletConfig config){
        loadContext(config);
        initStratery();
    }

    private void loadContext(ServletConfig config){
        String pathvalue = config.getInitParameter(SPRING_MVC_CONTEXT);
        //实用配置话获取spring mvc配置文件
        MyResources resources = new MyClassPathResource(pathvalue);
        beanFactory = new MyDefaultListableBeanFactory();
        MyXmlBeanDefinitionReader reader = new MyXmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions(resources);
    }

    private void initStratery(){
        handlerMappingList = beanFactory.getBeansByType(MyHandlerMapping.class);
        handlerAdapterList = beanFactory.getBeansByType(MyHandlerAdapter.class);
    }

    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查找处理器
        Object handler = getHandler(request);
        if(handler == null){
            return;
        }
        //执行处理器方法
        MyHandlerAdapter mha = getHandlerAdapter(handler);
        if(mha == null){
            return;
        }
        //执行并响应结果
        try {
            mha.handleRequest(handler, request, response);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据请求获取处理器
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        if(handlerMappingList != null && handlerMappingList.size()>0){
            for (MyHandlerMapping handlerMapping : handlerMappingList){
                Object handler = handlerMapping.getHandler(request);
                if(handler != null){
                    return handler;
                }
            }
        }
        return null;
    }


    /**
     * 根据处理器查找适配器
     * @param handler
     * @return
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        if(handlerAdapterList != null && handlerAdapterList.size() >0){
            for (MyHandlerAdapter handlerAdapter : handlerAdapterList){
                if(handlerAdapter.support(handler)){
                    return handlerAdapter;
                }
            }
        }
        return null;
    }

}
