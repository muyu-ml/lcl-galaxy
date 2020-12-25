package com.lcl.galaxy.springmvc.frame.servlet;

import com.lcl.galaxy.springmvc.frame.adapter.MyHandlerAdapter;
import com.lcl.galaxy.springmvc.frame.adapter.MyHttpRequestHandlerAdapter;
import com.lcl.galaxy.springmvc.frame.adapter.MySimpleContrpllerHandlerAdapter;
import com.lcl.galaxy.springmvc.frame.handler.MyHttpRequestHandler;
import com.lcl.galaxy.springmvc.frame.handler.MySimpleControllerHandler;
import com.lcl.galaxy.springmvc.frame.handlermapping.MyBeanNameUrlHandlerMapping;
import com.lcl.galaxy.springmvc.frame.handlermapping.MyHandlerMapping;
import com.lcl.galaxy.springmvc.frame.handlermapping.MySimpleUrlHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyDispatcherServlet extends MyAbstructServlet {
    @Override
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //查找处理器
        Object handler = getHandler(request);
        //执行处理器方法
        MyHandlerAdapter mha = getHandlerAdapter(handler);
        //执行并响应结果
        mha.handleRequest(handler, request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        if(handler instanceof MyHttpRequestHandler){
            return new MyHttpRequestHandlerAdapter();
        }else if(handler instanceof MySimpleControllerHandler){
            return new MySimpleContrpllerHandlerAdapter();
        }

        return null;
    }

    private Object getHandler(HttpServletRequest request) {
        MyHandlerMapping mhm = null;
        mhm = new MyBeanNameUrlHandlerMapping();
        Object handler = mhm.getHandler(request);
        if(handler != null){
            return handler;
        }

        mhm = new MySimpleUrlHandlerMapping();
        handler = mhm.getHandler(request);
        if(handler != null){
            return handler;
        }
        return null;
    }
}
